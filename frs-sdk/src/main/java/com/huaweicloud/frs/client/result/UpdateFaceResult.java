package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class UpdateFaceResult extends JSONObj {

    @JsonProperty(value = "face_set_name")
    private String faceSetName;

    @JsonProperty(value = "face_set_id")
    private String faceSetID;

    @JsonProperty(value = "face_number")
    private Long faceNumber;

    public String getFaceSetName() {
        return faceSetName;
    }

    public void setFaceSetName(String faceSetName) {
        this.faceSetName = faceSetName;
    }

    public String getFaceSetID() {
        return faceSetID;
    }

    public void setFaceSetID(String faceSetID) {
        this.faceSetID = faceSetID;
    }

    public Long getFaceNumber() {
        return faceNumber;
    }

    public void setFaceNumber(Long faceNumber) {
        this.faceNumber = faceNumber;
    }
}
