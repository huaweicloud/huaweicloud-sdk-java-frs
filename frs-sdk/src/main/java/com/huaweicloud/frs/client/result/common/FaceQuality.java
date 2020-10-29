package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class FaceQuality extends JSONObj {

    @JsonProperty(value = "total_score")
    private double total_score;

    @JsonProperty(value = "blur")
    private double blur;

    @JsonProperty(value = "pose")
    private double pose;

    @JsonProperty(value = "occlusion")
    private double occlusion;

    @JsonProperty(value = "illumination")
    private double illumination;

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }

    public double getBlur() {
        return blur;
    }

    public void setBlur(double blur) {
        this.blur = blur;
    }

    public double getPose() {
        return pose;
    }

    public void setPose(double pose) {
        this.pose = pose;
    }

    public double getOcclusion() {
        return occlusion;
    }

    public void setOcclusion(double occlusion) {
        this.occlusion = occlusion;
    }

    public double getIllumination() {
        return illumination;
    }

    public void setIllumination(double illumination) {
        this.illumination = illumination;
    }
}
