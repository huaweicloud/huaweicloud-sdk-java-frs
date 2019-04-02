package com.huaweicloud.frs.client.service.v2;

import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.service.*;

public class ApiCollectionV2 {

    private CompareServiceV2 compareService;

    private DetectServiceV2 detectService;

    private FaceServiceV2 faceService;

    private FaceSetServiceV2 faceSetService;

    private LiveDetectServiceV2 liveDetectService;

    private SearchServiceV2 searchService;

    public ApiCollectionV2(FrsAccess service, String projectId) {
        this.detectService = new DetectServiceV2(service, projectId);
        this.compareService = new CompareServiceV2(service, projectId);
        this.searchService = new SearchServiceV2(service, projectId);
        this.faceService = new FaceServiceV2(service, projectId);
        this.faceSetService = new FaceSetServiceV2(service, projectId);
        this.liveDetectService = new LiveDetectServiceV2(service, projectId);
    }

    public CompareServiceV2 getCompareService() {
        return compareService;
    }

    public DetectServiceV2 getDetectService() {
        return detectService;
    }

    public FaceServiceV2 getFaceService() {
        return faceService;
    }

    public FaceSetServiceV2 getFaceSetService() {
        return faceSetService;
    }

    public LiveDetectServiceV2 getLiveDetectService() {
        return liveDetectService;
    }

    public SearchServiceV2 getSearchService() {
        return searchService;
    }
}
