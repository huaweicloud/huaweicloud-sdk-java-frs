package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

/**
 * Result of blur classify
 */
public class BlurClassifyResult extends JSONObj {

    @JsonProperty(value = "isClarity")
    private boolean isClarity;

    public boolean isClarity() {
        return isClarity;
    }

    public void setClarity(boolean clarity) {
        isClarity = clarity;
    }

    public String toString() {
        return String.format("{\"isClarity\":%s}", isClarity ? "true" : "false");
    }

}
