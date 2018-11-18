package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.FaceSet;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

/**
 * Result of getting all face set
 */
public class GetAllFaceSetsResult extends JSONObj {

    @JsonProperty(value = "face_sets_info")
    private List<FaceSet> faceSetsInfo;

    public GetAllFaceSetsResult() {
    }

    public List<FaceSet> getFaceSetsInfo() {
        return this.faceSetsInfo;
    }

    public void setFaceSetsInfo(List<FaceSet> faceSetsInfo) {
        this.faceSetsInfo = faceSetsInfo;
    }

    public String toString() {
        return String.format("{\"faceSetsInfo\":%s}", this.faceSetsInfo.toString());
    }

}
