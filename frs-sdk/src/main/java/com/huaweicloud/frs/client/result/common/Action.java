package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class Action extends JSONObj {

    @JsonProperty(value = "action")
    private int action;

    @JsonProperty(value = "confidence")
    private double confidence;

    public Action() {

    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String toString() {
        return String.format("{\"action\":%d,\"confidence\":%s}", action, String.valueOf(confidence));
    }
}
