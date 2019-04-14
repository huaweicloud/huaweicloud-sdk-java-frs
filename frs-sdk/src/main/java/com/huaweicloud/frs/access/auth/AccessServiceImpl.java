package com.huaweicloud.frs.access.auth;


import com.cloud.sdk.DefaultRequest;
import com.cloud.sdk.Request;
import com.cloud.sdk.auth.credentials.BasicCredentials;
import com.cloud.sdk.auth.signer.Signer;
import com.cloud.sdk.auth.signer.SignerFactory;
import com.cloud.sdk.http.HttpMethodName;
import com.huaweicloud.frs.access.utils.HttpClientUtils;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Header;
import okio.Buffer;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;


public class AccessServiceImpl extends AccessService {

    private OkHttpClient client = null;

    public AccessServiceImpl(String serviceName, String region, String ak, String sk) {
        super(serviceName, region, ak, sk);
    }

    /**
     * Make a request that can be sent by the HTTP client.
     *
     * @param url           specifies the API access path.
     * @param header        specifies the header information to be added.
     * @param content       specifies the body content to be sent in the API call.
     * @param contentLength specifies the length of the content. This parameter is optional.
     * @param httpMethod    specifies the HTTP method to be used.
     * @return specifies the request that can be sent by an HTTP client.
     */
    private static okhttp3.Request createRequest(URL url, Header header, RequestBody content, Long contentLength,
                                                 HttpMethodName httpMethod) {

        okhttp3.Request httpRequest;
        if (httpMethod == HttpMethodName.POST) {
            okhttp3.Request.Builder postMethod = new okhttp3.Request.Builder();
            postMethod.url(url);

            if (content != null) {
                postMethod.post(content);
            }
            httpRequest = postMethod.build();
        } else if (httpMethod == HttpMethodName.PUT) {
            okhttp3.Request.Builder putMethod = new okhttp3.Request.Builder();
            putMethod.url(url);
            httpRequest = putMethod.build();

            if (content != null) {
                putMethod.put(content);
            }
        } else if (httpMethod == HttpMethodName.PATCH) {
            okhttp3.Request.Builder patchMethod = new okhttp3.Request.Builder();
            patchMethod.url(url);
            httpRequest = patchMethod.build();

            if (content != null) {
                patchMethod.patch(content);
            }
        } else if (httpMethod == HttpMethodName.GET) {
            httpRequest = new okhttp3.Request.Builder().url(url).get().build();
        } else if (httpMethod == HttpMethodName.DELETE) {
            httpRequest = new okhttp3.Request.Builder().url(url).delete().build();
        } else if (httpMethod == HttpMethodName.HEAD) {
            httpRequest = new okhttp3.Request.Builder().url(url).head().build();
        } else {
            throw new RuntimeException("Unknown HTTP method name: " + httpMethod);
        }

        httpRequest.newBuilder().addHeader(header.name.toString(), header.value.toString());
        return httpRequest;
    }

    private static okhttp3.Request createRequestEntity(URL url, Header header, RequestBody requestBody,
                                                       HttpMethodName httpMethod) {
        okhttp3.Request httpRequest;
        if (httpMethod == HttpMethodName.POST) {
            okhttp3.Request.Builder postMethod = new okhttp3.Request.Builder();
            postMethod.url(url);
            if (requestBody != null) {
                postMethod.post(requestBody);
            }
            httpRequest = postMethod.build();
        } else if (httpMethod == HttpMethodName.PUT) {
            okhttp3.Request.Builder putMethod = new okhttp3.Request.Builder();
            putMethod.url(url);
            if (requestBody != null) {
                putMethod.put(requestBody);
            }
            httpRequest = putMethod.build();
        } else if (httpMethod == HttpMethodName.PATCH) {
            okhttp3.Request.Builder patchMethod = new okhttp3.Request.Builder();
            patchMethod.url(url);
            if (requestBody != null) {
                patchMethod.patch(requestBody);
            }
            httpRequest = patchMethod.build();

        } else if (httpMethod == HttpMethodName.GET) {
            httpRequest = new okhttp3.Request.Builder().url(url).get().build();
        } else if (httpMethod == HttpMethodName.DELETE) {
            httpRequest = new okhttp3.Request.Builder().url(url).delete().build();
        } else if (httpMethod == HttpMethodName.HEAD) {
            httpRequest = new okhttp3.Request.Builder().url(url).head().build();
        } else {
            throw new RuntimeException("Unknown HTTP method name: " + httpMethod);
        }
        if (header != null) {
            httpRequest = httpRequest.newBuilder().addHeader(header.name.toString(), header.value.toString()).build();
        }
        return httpRequest;
    }

    protected OkHttpClient getHttpClient() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return HttpClientUtils.acceptsUntrustedCertsHttpClient();
    }

    private OkHttpClient getDefaultHttpClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        X509TrustManager x509m = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        };
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, new TrustManager[]{x509m}, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.sslSocketFactory(sslSocketFactory, x509m);
        return httpClient.build();
    }

    protected boolean useDefaultHttpClient() {
        return true;
    }

    public Response access(URL url, Map<String, String> headers, RequestBody content, Long contentLength,
                           HttpMethodName httpMethod) throws Exception {

        // Make a request for signing.
        Request request = new DefaultRequest();
        try {
            // Set the request address.
            request.setEndpoint(url.toURI());

            String urlString = url.toString();

            String parameters = null;

            if (urlString.contains("?")) {
                parameters = urlString.substring(urlString.indexOf("?") + 1);
                Map parametersmap = new HashMap<String, String>();

                if (null != parameters && !"".equals(parameters)) {
                    String[] parameterarray = parameters.split("&");

                    for (String p : parameterarray) {
                        String key = p.split("=")[0];
                        String value = p.split("=")[1];
                        parametersmap.put(key, value);
                    }
                    request.setParameters(parametersmap);
                }
            }

        } catch (URISyntaxException e) {
            // It is recommended to add logs in this place.
            e.printStackTrace();
        }
        // Set the request method.
        request.setHttpMethod(httpMethod);
        if (headers != null) {
            // Add request header information if required.
            request.setHeaders(headers);
        }
        // Configure the request content.
//        BufferedSink sink = null;
//        content.writeTo(sink);
//        request.setContent(sink.buffer().inputStream());

        // Select an algorithm for request signing.
        Signer signer = SignerFactory.getSigner(serviceName, region);
        // Sign the request, and the request will change after the signing.
        signer.sign(request, new BasicCredentials(this.ak, this.sk));

        // Make a request that can be sent by the HTTP client.
        okhttp3.Request httpRequestBase = createRequest(url, null, content, contentLength, httpMethod);
        Map<String, String> requestHeaders = request.getHeaders();

        // Put the header of the signed request to the new request.
        for (String key : requestHeaders.keySet()) {
            if (key.equalsIgnoreCase("Content-Length")) {
                continue;
            }
            httpRequestBase = httpRequestBase.newBuilder().addHeader(key, requestHeaders.get(key)).build();
        }

        client = useDefaultHttpClient() ? getDefaultHttpClient() : getHttpClient();

        // Send the request, and a response will be returned.
        Response response = client.newCall(httpRequestBase).execute();
        return response;
    }

    @Override
    public Response accessEntity(URL url, Map<String, String> header, RequestBody requestBody,
                                 Long contentLength, HttpMethodName httpMethod)
            throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException{
        // Make a request for signing.
        Request request = new DefaultRequest();
        try {
            // Set the request address.
            request.setEndpoint(url.toURI());

            String urlString = url.toString();

            String parameters = null;

            if (urlString.contains("?")) {
                parameters = urlString.substring(urlString.indexOf("?") + 1);
                Map parametersmap = new HashMap<String, String>();

                if (null != parameters && !"".equals(parameters)) {
                    String[] parameterarray = parameters.split("&");

                    for (String p : parameterarray) {
                        String key = p.split("=")[0];
                        String value = p.split("=")[1];
                        parametersmap.put(key, value);
                    }
                    request.setParameters(parametersmap);
                }
            }

        } catch (URISyntaxException e) {
            // It is recommended to add logs in this place.
            e.printStackTrace();
        }
        // Set the request method.
        request.setHttpMethod(httpMethod);
        if (header != null) {
            request.setHeaders(header);
        }
        // Configure the request content.
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            request.setContent(new ByteArrayInputStream(buffer.readByteArray()));
        }
        // Select an algorithm for request signing.
        Signer signer = SignerFactory.getSigner(serviceName, region);
        // Sign the request, and the request will change after the signing.
        signer.sign(request, new BasicCredentials(this.ak, this.sk));

        // Make a request that can be sent by the HTTP client.
        okhttp3.Request httpRequestBase = createRequestEntity(url, null, requestBody, httpMethod);
        final Map<String, String> requestHeaders = request.getHeaders();
        // Put the header of the signed request to the new request.
        for (String key : requestHeaders.keySet()) {
            if (key.equalsIgnoreCase("Content-Length")) {
                continue;
            }
            httpRequestBase = httpRequestBase.newBuilder().addHeader(key, requestHeaders.get(key)).build();
        }

        client = useDefaultHttpClient() ? getDefaultHttpClient() : getHttpClient();
//		Headers testHeader=httpRequestBase.headers();
        Response response = client.newCall(httpRequestBase).execute();
        return response;
    }

}
