package com.huaweicloud.frs.access;

import com.cloud.sdk.http.HttpMethodName;
import com.huaweicloud.frs.access.auth.AccessServiceImpl;
import com.huaweicloud.frs.access.utils.HttpClientUtils;
import com.huaweicloud.frs.client.param.AuthInfo;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class FrsAccess extends AccessServiceImpl {

    /**
     * Service name
     */
    private static final String SERVICE_NAME = "frs";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public int connectionTimeout = HttpClientUtils.DEFAULT_CONNECTION_TIMEOUT;
    /**
     * Authentication information
     */
    private AuthInfo authInfo = null;

    public FrsAccess(AuthInfo authInfo) throws Exception {
        super(FrsAccess.SERVICE_NAME, authInfo.getRegion(), authInfo.getAk(), authInfo.getSk());
        this.authInfo = authInfo;
    }

    public FrsAccess(AuthInfo authInfo, int connectionTimeout) throws Exception {
        super(FrsAccess.SERVICE_NAME, authInfo.getRegion(), authInfo.getAk(), authInfo.getSk());
        this.authInfo = authInfo;

        this.connectionTimeout = connectionTimeout;

    }

    //
    // Generate the whole url for the specific ais service
    //
    private static String generateWholeUrl(String endPoint, String uri) {
        return String.format("%s%s", endPoint, uri);
    }

    @Override
    protected OkHttpClient getHttpClient()
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return HttpClientUtils.acceptsUntrustedCertsHttpClient(false, null, this.connectionTimeout);
    }

    protected boolean useDefaultHttpClient() {
        return false;
    }

    public Response put(String requestUrl, String putBody) {

        Response response = null;
        try {
            URL url = new URL(generateWholeUrl(authInfo.getEndPoint(), requestUrl));
            HttpMethodName httpMethod = HttpMethodName.PUT;

            RequestBody requestBody = RequestBody.create(JSON, putBody);
            response = access(url, requestBody, (long) putBody.getBytes().length, httpMethod);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public Response get(String requestUrl) {

        Response response = null;
        try {
            URL url = new URL(generateWholeUrl(authInfo.getEndPoint(), requestUrl));
            HttpMethodName httpMethod = HttpMethodName.GET;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json; charset=utf-8");
            response = access(url, header, httpMethod);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public Response post(String requestUrl, RequestBody requestBody) throws IOException {

        URL url = null;
        try {
            url = new URL(generateWholeUrl(authInfo.getEndPoint(), requestUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpMethodName httpMethod = HttpMethodName.POST;
        Response response = null;
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", requestBody.contentType().toString());
        try {
            response = accessEntity(url, header, requestBody, (long) requestBody.contentLength(), httpMethod);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    public Response delete(String requestUrl) {
        Response response = null;

        try {
            URL url = new URL(generateWholeUrl(this.authInfo.getEndPoint(), requestUrl));
            HttpMethodName httpMethod = HttpMethodName.DELETE;
            Map<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json; charset=utf-8");
            response = this.access(url, header, httpMethod);
            return response;
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

}
