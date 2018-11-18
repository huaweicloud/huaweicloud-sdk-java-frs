package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of estimating head pose
 */
public class HeadPoseEstimateResult extends JSONObj {

    @JsonProperty(value = "yaw")
    private double yaw;

    @JsonProperty(value = "roll")
    private double roll;

    @JsonProperty(value = "pitch")
    private double pitch;

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public String toString() {
        return String.format("{\"yaw\":%s,\"roll\":%s,\"pitch\":%s}",
                String.valueOf(yaw), String.valueOf(roll), String.valueOf(pitch));
    }

}
