package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.FaceSet;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of getting face set
 */
public class GetFaceSetResult extends JSONObj {

    @JsonProperty(value = "face_set_info")
    private FaceSet faceSetInfo;

    public GetFaceSetResult() {
    }

    public FaceSet getFaceSetInfo() {
        return this.faceSetInfo;
    }

    public void setFaceSetInfo(FaceSet faceSetInfo) {
        this.faceSetInfo = faceSetInfo;
    }

    public String toString() {
        return String.format("{\"faceSetInfo\":%s}", this.faceSetInfo.toString());
    }

}
