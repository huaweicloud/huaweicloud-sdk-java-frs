package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class FaceExpression extends JSONObj {

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "probability")
    private double probability;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
