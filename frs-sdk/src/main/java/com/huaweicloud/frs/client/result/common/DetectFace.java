package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetectFace extends SimpleFace {

    @JsonProperty(value = "attributes")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    protected Attributes attributes;

    @JsonProperty(value = "landmark")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    protected Landmark landmark;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Landmark getLandmark() {
        return landmark;
    }

    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
    }

    public String toString() {
        if (null != landmark && null != attributes) {
            return String.format("{\"boundingBox\":%s,\"attributes\":%s,\"landmark\":%s}",
                    this.boundingBox.toString(), this.attributes.toString(), this.landmark.toString());
        } else if (null == landmark && null != attributes) {
            return String.format("{\"boundingBox\":%s,\"attributes\":%s}",
                    this.boundingBox.toString(), this.attributes.toString());
        } else if (null != landmark) {
            return String.format("{\"boundingBox\":%s,\"landmark\":%s}",
                    this.boundingBox.toString(), this.landmark.toString());
        } else {
            return String.format("{\"boundingBox\":%s}",
                    this.boundingBox.toString());
        }
    }

}
