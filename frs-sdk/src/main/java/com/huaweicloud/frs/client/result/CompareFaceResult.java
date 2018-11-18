package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.SimpleFace;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of face compare
 */
public class CompareFaceResult extends JSONObj {

    @JsonProperty(value = "similarity")
    private double similarity;

    @JsonProperty(value = "image1_face")
    private SimpleFace image1Face;

    @JsonProperty(value = "image2_face")
    private SimpleFace image2Face;

    public CompareFaceResult() {
    }

    public double getSimilarity() {
        return this.similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public SimpleFace getImage1Face() {
        return this.image1Face;
    }

    public void setImage1Face(SimpleFace image1Face) {
        this.image1Face = image1Face;
    }

    public SimpleFace getImage2Face() {
        return this.image2Face;
    }

    public void setImage2Face(SimpleFace image2Face) {
        this.image2Face = image2Face;
    }

    public String toString() {
        return String.format("{\"image1Face\":%s,\"similarity\":%s,\"image2Face\":%s}", this.image1Face.toString(), String.valueOf(this.similarity), this.image2Face.toString());
    }

}
