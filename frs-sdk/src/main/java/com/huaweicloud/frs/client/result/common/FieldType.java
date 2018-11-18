package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class FieldType extends JSONObj {

    @JsonProperty(value = "type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
