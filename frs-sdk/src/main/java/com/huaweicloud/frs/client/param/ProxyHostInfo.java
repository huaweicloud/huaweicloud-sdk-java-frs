package com.huaweicloud.frs.client.param;

public class ProxyHostInfo {
    private String hostName = "";
    private int port = 8080;
    private String userName = "";
    private String password = "";

    public ProxyHostInfo(String hostName, int port, String userName, String password) {
        this.hostName = hostName;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
