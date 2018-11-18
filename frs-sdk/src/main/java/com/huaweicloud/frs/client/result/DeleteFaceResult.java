package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of face delete
 */
public class DeleteFaceResult extends JSONObj {

    @JsonProperty(value = "face_number")
    private int faceNumber;

    @JsonProperty(value = "face_set_id")
    private String faceSetId;

    @JsonProperty(value = "face_set_name")
    private String faceSetName;

    public int getFaceNumber() {
        return faceNumber;
    }

    public void setFaceNumber(int faceNumber) {
        this.faceNumber = faceNumber;
    }

    public String getFaceSetId() {
        return this.faceSetId;
    }

    public void setFaceSetId(String faceSetId) {
        this.faceSetId = faceSetId;
    }

    public String getFaceSetName() {
        return this.faceSetName;
    }

    public void setFaceSetName(String faceSetName) {
        this.faceSetName = faceSetName;
    }

    public String toString() {
        return String.format("{\"faceNumber\":%d,\"faceSetId\":\"%s\",\"faceSetName\":\"%s\"}",
                this.faceNumber, this.faceSetId, this.faceSetName);
    }

}
