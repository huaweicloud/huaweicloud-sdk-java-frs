package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.VideoResult;
import com.huaweicloud.frs.client.result.common.Warning;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

/**
 * Result of live detect
 */
public class LiveDetectResult extends JSONObj {

    @JsonProperty(value = "video-result")
    private VideoResult videoResult;

    @JsonProperty(value = "warning-list")
    private List<Warning> warningList;

    public VideoResult getVideoResult() {
        return videoResult;
    }

    public void setVideoResult(VideoResult videoResult) {
        this.videoResult = videoResult;
    }

    public List<Warning> getWarningList() {
        return warningList;
    }

    public void setWarningList(List<Warning> warningList) {
        this.warningList = warningList;
    }

    public String toString() {
        return String.format("{\"videoResult\":%s,\"warningList\":%s}",
                videoResult.toString(), warningList.toString());
    }

}
