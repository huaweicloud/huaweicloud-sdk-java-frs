package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class BoundingBox extends JSONObj {

    @JsonProperty(value = "width")
    private int width;

    @JsonProperty(value = "height")
    private int height;

    @JsonProperty(value = "top_left_x")
    private int topLeftX;

    @JsonProperty(value = "top_left_y")
    private int topLeftY;

    public BoundingBox() {
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTopLeftX() {
        return this.topLeftX;
    }

    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    public int getTopLeftY() {
        return this.topLeftY;
    }

    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    public String toString() {
        return String.format("{\"width\":%d,\"height\":%d,\"topLeftX\":%d,\"topLeftY\":%d}", this.width, this.height, this.topLeftX, this.topLeftY);
    }
}
