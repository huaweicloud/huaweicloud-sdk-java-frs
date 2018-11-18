package com.huaweicloud.frs.client.param;

public class AuthInfo {
    private String endPoint = "";
    private String region = "";
    private String ak = "";
    private String sk = "";

    public AuthInfo() {
    }

    public AuthInfo(String endPoint, String region, String ak, String sk) {
        this.endPoint = endPoint;
        this.region = region;
        this.ak = ak;
        this.sk = sk;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public String getRegion() {
        return this.region;
    }

    public String getAk() {
        return this.ak;
    }

    public String getSk() {
        return this.sk;
    }
}
