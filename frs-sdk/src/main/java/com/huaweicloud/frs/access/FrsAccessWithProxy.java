package com.huaweicloud.frs.access;

import com.huaweicloud.frs.access.utils.HttpClientUtils;
import com.huaweicloud.frs.client.param.AuthInfo;
import com.huaweicloud.frs.client.param.ProxyHostInfo;
import okhttp3.OkHttpClient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class FrsAccessWithProxy extends FrsAccess {

    /**
     * Proxy host information
     */
    private ProxyHostInfo proxyHostInfo = null;

    public FrsAccessWithProxy(AuthInfo authInfo, ProxyHostInfo hostInfo) {
        super(authInfo);
        proxyHostInfo = hostInfo;
    }

    public FrsAccessWithProxy(AuthInfo authInfo, ProxyHostInfo hostInfo, int connectionTimeout) {
        super(authInfo);
        proxyHostInfo = hostInfo;
        this.connectionTimeout = connectionTimeout;

    }


    @Override
    protected OkHttpClient getHttpClient()
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        return HttpClientUtils.acceptsUntrustedCertsHttpClient(true, proxyHostInfo, this.connectionTimeout);
    }

}
