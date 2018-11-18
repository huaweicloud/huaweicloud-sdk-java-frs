package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class SimpleFace extends JSONObj {

    @JsonProperty(value = "bounding_box")
    protected BoundingBox boundingBox;

    public SimpleFace() {
    }

    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String toString() {
        return String.format("{\"boundingBox\":%s}", this.boundingBox.toString());
    }

}
