package com.huaweicloud.frs.access.utils;


import com.huaweicloud.frs.client.param.ProxyHostInfo;
import okhttp3.*;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {

    public static int DEFAULT_CONNECTION_TIMEOUT = 5000;
    private static MyTrustManager myTrustManager;

    public static OkHttpClient acceptsUntrustedCertsHttpClient(boolean withProxy, ProxyHostInfo hostInfo, int connectionTimeout) {
        OkHttpClient.Builder b = new OkHttpClient.Builder();

        /**
         * set http proxy
         */
        b.connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS);

        if (withProxy) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostInfo.getHostName(), hostInfo.getPort()));
            b.proxy(proxy);
            final String credentials = Credentials.basic(hostInfo.getUserName(), hostInfo.getPassword());
            b.proxyAuthenticator((route, response) -> {
                if (response.request().header("Proxy-Authorization") != null) {
                    return null;
                }
                return response.request().newBuilder().header("Proxy-Authorization", credentials).build();
            });
        }

        b.sslSocketFactory(createSSLSocketFactory(), myTrustManager).hostnameVerifier(new TrustAllHostnameVerifier());
        OkHttpClient client = b.build();

        return client;
    }

    public static OkHttpClient acceptsUntrustedCertsHttpClient() {
        return acceptsUntrustedCertsHttpClient(false, null, DEFAULT_CONNECTION_TIMEOUT);
    }

    public static OkHttpClient acceptsUntrustedCertsHttpClient(int connectionTimeout) {
        return acceptsUntrustedCertsHttpClient(false, null, connectionTimeout);
    }

    public static Response post(String url, Headers headers, RequestBody entity, int connectionTimeout) {
        Response response = null;
        OkHttpClient httpClient = null;
        try {
            httpClient = acceptsUntrustedCertsHttpClient(connectionTimeout);
            Request.Builder post = new Request.Builder();
            post.url(url);
            if (null != headers) {
                post.headers(headers);
            }
            if (null != entity) {
                post.post(entity);
            }
            response = httpClient.newCall(post.build()).execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    public static Response post(String url, Headers headers, RequestBody entity) {
        Response response = null;
        OkHttpClient httpClient = null;
        try {
            httpClient = acceptsUntrustedCertsHttpClient();
            Request.Builder post = new Request.Builder();
            post.url(url);
            if (null != headers) {
                post.headers(headers);
            }
            if (null != entity) {
                post.post(entity);
            }
            response = httpClient.newCall(post.build()).execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    public static Response get(String url, Headers headers) {
        Response response = null;
        OkHttpClient httpClient = null;
        try {
            httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
            Request.Builder get = new Request.Builder();
            get.url(url);
            if (null != headers) {
                get.headers(headers);
            }
            response = httpClient.newCall(get.build()).execute();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            myTrustManager = new MyTrustManager();
            SSLContext sc = null;
            sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, new TrustManager[]{myTrustManager}, new SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }

    private static class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

}
