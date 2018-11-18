package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of face quality
 */
public class FaceQualityResult extends JSONObj {

    @JsonProperty(value = "blur")
    private BlurClassifyResult blur;

    @JsonProperty(value = "pose")
    private HeadPoseEstimateResult pose;

    public BlurClassifyResult getBlur() {
        return blur;
    }

    public void setBlur(BlurClassifyResult blur) {
        this.blur = blur;
    }

    public HeadPoseEstimateResult getPose() {
        return pose;
    }

    public void setPose(HeadPoseEstimateResult pose) {
        this.pose = pose;
    }

    public String toString() {
        return String.format("{\"blur\":%s,\"pose\":%s}", blur.toString(), pose.toString());
    }

}