package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

public class VideoResult extends JSONObj {

    @JsonProperty(value = "alive")
    private boolean alive;

    @JsonProperty(value = "actions")
    private List<Action> actions;

    @JsonProperty(value = "picture")
    private String picture;

    public VideoResult() {

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String toString() {
        return String.format("{\"alive\":%s,\"actions\":%s,\"picture\":\"%s\"}",
                alive ? "true" : "false", actions.toString(), picture);
    }

}
