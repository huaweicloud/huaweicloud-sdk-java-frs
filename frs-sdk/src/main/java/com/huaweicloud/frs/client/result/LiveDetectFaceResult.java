package com.huaweicloud.frs.client.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.client.result.common.FaceLivenessResult;
import com.huaweicloud.frs.client.result.common.Warning;
import com.huaweicloud.frs.common.JSONObj;

import java.util.List;

/**
 * Result of live detect face
 */
public class LiveDetectFaceResult extends JSONObj {

    @JsonProperty(value = "result")
    private FaceLivenessResult faceLivenessResult;

    @JsonProperty(value = "warning-list")
    private List<Warning> warningList;

    @JsonProperty(value = "error_code")
    private Integer errCode;

    @JsonProperty(value = "error_msg")
    private Integer errorMsg;

    public FaceLivenessResult getFaceLivenessResult() {
        return faceLivenessResult;
    }

    public void setFaceLivenessResult(FaceLivenessResult faceLivenessResult) {
        this.faceLivenessResult = faceLivenessResult;
    }

    public List<Warning> getWarningList() {
        return warningList;
    }

    public void setWarningList(List<Warning> warningList) {
        this.warningList = warningList;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public Integer getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Integer errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return String.format("{\"faceLivenessResult\":%s,\"warningList\":%s}",
                faceLivenessResult.toString(), warningList.toString());
    }

}
