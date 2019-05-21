package com.huaweicloud.frs.access;


import com.huaweicloud.frs.client.param.AuthInfo;
import com.huaweicloud.frs.client.param.ProxyHostInfo;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class FrsAccessClient {

    /**
     * Singleton
     */
    private static FrsAccessClient instance = new FrsAccessClient();

    /**
     * Proxy flag
     */
    private boolean accessWithProxy = false;

    /**
     * Proxy host information
     */
    private ProxyHostInfo proxyHostInfo = null;

    /**
     * Authentication information
     */
    private AuthInfo authInfo = null;

    /**
     * Get singleton
     *
     * @return FrsAccessClient instance
     */
    public static FrsAccessClient getInstance() {
        return instance;
    }

    public void init(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public FrsAccess getAccessService(AuthInfo inAuthInfo) throws Exception {
        return accessWithProxy ? new FrsAccessWithProxy(inAuthInfo, proxyHostInfo) : new FrsAccess(inAuthInfo);
    }

    public FrsAccess getAccessService() throws Exception {
        return accessWithProxy ? new FrsAccessWithProxy(authInfo, proxyHostInfo) : new FrsAccess(authInfo);
    }

    public FrsAccessClient setAccessWithProxy(boolean accessWithProxy) {
        this.accessWithProxy = accessWithProxy;
        return this;
    }

    public FrsAccessClient setProxyHostInfo(ProxyHostInfo proxyHostInfo) {
        this.proxyHostInfo = proxyHostInfo;
        return this;
    }
}
