package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of face set delete
 */
public class DeleteFaceSetResult extends JSONObj {

    @JsonProperty(value = "face_set_name")
    private String faceSetName;

    public DeleteFaceSetResult() {
    }

    public String getFaceSetName() {
        return this.faceSetName;
    }

    public void setFaceSetName(String faceSetName) {
        this.faceSetName = faceSetName;
    }

    public String toString() {
        return String.format("{\"faceSetName\":\"%s\"}", this.faceSetName);
    }

}
