package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ComplexFace extends Face {

    @JsonProperty(value = "similarity")
    private double similarity;

    public ComplexFace() {
    }

    public double getSimilarity() {
        return this.similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public String toString() {
        if (null == this.externalFields) {
            return String.format("{\"boundingBox\":%s,\"similarity\":%s,\"externalImageId\":\"%s\",\"faceId\":\"%s\"}",
                    this.boundingBox.toString(), String.valueOf(this.similarity), this.externalImageId, this.faceId);
        } else {
            return String.format("{\"boundingBox\":%s,\"similarity\":%s,\"externalImageId\":\"%s\",\"externalFields\":\"%s\",\"faceId\":\"%s\"}",
                    this.boundingBox.toString(), String.valueOf(this.similarity), this.externalImageId, this.externalFields, this.faceId);
        }
    }

}
