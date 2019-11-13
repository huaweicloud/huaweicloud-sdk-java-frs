package com.huaweicloud.frs.access.auth;

import com.cloud.sdk.http.HttpMethodName;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URL;
import java.util.Map;

public abstract class AccessService {

    protected String serviceName = null;

    protected String region = null;

    protected String ak = null;

    protected String sk = null;

    public AccessService(String serviceName, String region, String ak, String sk) {
        this.region = region;
        this.serviceName = serviceName;
        this.ak = ak;
        this.sk = sk;
    }


    public abstract Response accessEntity(URL url, Map<String, String> header, RequestBody entity,
                                          Long contentLength, HttpMethodName httpMethod) throws Exception;

    public abstract Response access(URL url, Map<String, String> header, HttpMethodName httpMethod) throws Exception;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

}