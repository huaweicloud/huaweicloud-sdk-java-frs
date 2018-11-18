package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.ComplexFace;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

/**
 * Result of searching face
 */
public class SearchFaceResult extends JSONObj {

    @JsonProperty(value = "faces")
    private List<ComplexFace> faces;

    public SearchFaceResult() {
    }

    public List<ComplexFace> getFaces() {
        return this.faces;
    }

    public void setFaces(List<ComplexFace> faces) {
        this.faces = faces;
    }

    public String toString() {
        return String.format("{\"faces\":%s}", this.faces.toString());
    }

}
