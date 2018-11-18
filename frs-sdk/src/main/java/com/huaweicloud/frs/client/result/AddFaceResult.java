package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.Face;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

/**
 * Result of face add operation
 */
public class AddFaceResult extends JSONObj {

    @JsonProperty(value = "face_set_id")
    private String faceSetId;

    @JsonProperty(value = "face_set_name")
    private String faceSetName;

    @JsonProperty(value = "faces")
    private List<Face> faces;

    public AddFaceResult() {
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

    public List<Face> getFaces() {
        return this.faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public String toString() {
        return String.format("{\"faceSetId\":\"%s\",\"faceSetName\":\"%s\",\"faces\":%s}", this.faceSetId, this.faceSetName, this.faces.toString());
    }

}
