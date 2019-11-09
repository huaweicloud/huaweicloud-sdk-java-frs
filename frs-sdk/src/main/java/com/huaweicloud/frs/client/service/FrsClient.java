package com.huaweicloud.frs.client.service;

import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.access.FrsAccessWithProxy;
import com.huaweicloud.frs.client.param.AuthInfo;
import com.huaweicloud.frs.client.param.ProxyHostInfo;
import com.huaweicloud.frs.client.service.v2.ApiCollectionV2;

/**
 * SDK main entry.
 * Init with auth info and project id. Provides service instance.
 * Supports http proxy.
 */
public class FrsClient implements AutoCloseable {
    private AuthInfo authInfo;
    private String projectId;
    private FrsAccess service;
    private DetectService detectService;
    private CompareService compareService;
    private SearchService searchService;
    private FaceService faceService;
    private FaceSetService faceSetService;
    private LiveDetectService liveDetectService;

    private ApiCollectionV2 apiCollectionV2;

    /**
     * Construct client with auth info and project id
     *
     * @param authInfo  Authentication information
     * @param projectId Project id
     */
    public FrsClient(AuthInfo authInfo, String projectId) {
        this.init(authInfo, projectId, null);
    }

    /**
     * Construct client with auth info, project id and proxy info
     *
     * @param authInfo      Authentication information
     * @param projectId     Project id
     * @param proxyHostInfo Proxy information
     */
    public FrsClient(AuthInfo authInfo, String projectId, ProxyHostInfo proxyHostInfo) {
        this.init(authInfo, projectId, proxyHostInfo);
    }

    /**
     * Construct client with auth info, project id, connection timeout, connection request timeout, socket timeout
     *
     * @param authInfo          Authentication information
     * @param projectId         Project id
     * @param connectionTimeout Connection timeout
     */
    public FrsClient(AuthInfo authInfo, String projectId, int connectionTimeout) {
        this.init(authInfo, projectId, null, connectionTimeout);
    }

    /**
     * Construct client with auth info, project id, proxy information, connection timeout, connection request timeout, socket timeout
     *
     * @param authInfo          Authentication information
     * @param projectId         Project id
     * @param proxyHostInfo     proxy information
     * @param connectionTimeout Connection timeout
     */
    public FrsClient(AuthInfo authInfo, String projectId, ProxyHostInfo proxyHostInfo, int connectionTimeout) {
        this.init(authInfo, projectId, proxyHostInfo, connectionTimeout);
    }

    private void initService() {
        this.detectService = new DetectService(this.service, this.projectId);
        this.compareService = new CompareService(this.service, this.projectId);
        this.searchService = new SearchService(this.service, this.projectId);
        this.faceService = new FaceService(this.service, this.projectId);
        this.faceSetService = new FaceSetService(this.service, this.projectId);
        this.liveDetectService = new LiveDetectService(this.service, this.projectId);

        this.apiCollectionV2 = new ApiCollectionV2(this.service, this.projectId);
    }

    private void init(AuthInfo authInfo, String projectId, ProxyHostInfo proxyHostInfo) {
        this.authInfo = authInfo;
        this.projectId = projectId;
        if (null == proxyHostInfo) {
            this.service = new FrsAccess(this.authInfo);
        } else {
            this.service = new FrsAccessWithProxy(this.authInfo, proxyHostInfo);
        }
        this.initService();
    }

    private void init(AuthInfo authInfo, String projectId, ProxyHostInfo proxyHostInfo, int connectionTimeout) {
        this.authInfo = authInfo;
        this.projectId = projectId;
        if (null == proxyHostInfo) {
            this.service = new FrsAccess(this.authInfo, connectionTimeout);
        } else {
            this.service = new FrsAccessWithProxy(this.authInfo, proxyHostInfo, connectionTimeout);
        }
        this.initService();
    }

    /**
     * Get face detect service instance
     *
     * @return Face detect service instance
     */
    public DetectService getDetectService() {
        return this.detectService;
    }

    /**
     * Get face compare service instance
     *
     * @return Face compare service instance
     */
    public CompareService getCompareService() {
        return this.compareService;
    }

    /**
     * Get face search service instance
     *
     * @return Faced search service instance
     */
    public SearchService getSearchService() {
        return this.searchService;
    }

    /**
     * Get face operation service instance
     *
     * @return Face operation service instance
     */
    public FaceService getFaceService() {
        return this.faceService;
    }

    /**
     * Get face set operation service instance
     *
     * @return Face set operation service instance
     */
    public FaceSetService getFaceSetService() {
        return this.faceSetService;
    }

    /**
     * Get Live detect service instance
     *
     * @return Live detect service instance
     */
    public LiveDetectService getLiveDetectService() {
        return this.liveDetectService;
    }


    public ApiCollectionV2 getV2() {
        return this.apiCollectionV2;
    }

    @Override
    public void close() throws Exception {

    }
}
