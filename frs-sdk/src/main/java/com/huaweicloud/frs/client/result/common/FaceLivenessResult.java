package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class FaceLivenessResult extends JSONObj {

    @JsonProperty(value = "alive")
    private boolean alive;

    @JsonProperty(value = "confidence")
    private Double confidence;

    @JsonProperty(value = "picture")
    private String picture;

    public FaceLivenessResult() {

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String toString() {
        return String.format("{\"alive\":%s,\"confidence\":%s,\"picture\":\"%s\"}",
                alive ? "true" : "false", confidence, picture);
    }

}
