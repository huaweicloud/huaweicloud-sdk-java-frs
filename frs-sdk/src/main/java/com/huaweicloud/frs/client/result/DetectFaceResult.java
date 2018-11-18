package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.DetectFace;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

/**
 * Result of face detect
 */
public class DetectFaceResult extends JSONObj {

    @JsonProperty(value = "faces")
    private List<DetectFace> faces;

    public DetectFaceResult() {
    }

    public List<DetectFace> getFaces() {
        return this.faces;
    }

    public void setFaces(List<DetectFace> faces) {
        this.faces = faces;
    }

    public String toString() {
        return String.format("{\"faces\":%s}", this.faces.toString());
    }

}
