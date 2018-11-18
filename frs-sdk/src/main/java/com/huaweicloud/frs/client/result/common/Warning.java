package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Warning {

    @JsonProperty(value = "warningCode")
    private int warningCode;

    @JsonProperty(value = "warningMsg")
    private String warningMsg;

    public int getWarningCode() {
        return warningCode;
    }

    public void setWarningCode(int warningCode) {
        this.warningCode = warningCode;
    }

    public String getWarningMsg() {
        return warningMsg;
    }

    public void setWarningMsg(String warningMsg) {
        this.warningMsg = warningMsg;
    }
}
