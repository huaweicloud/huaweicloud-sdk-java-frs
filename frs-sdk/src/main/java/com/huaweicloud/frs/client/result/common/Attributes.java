package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class Attributes extends JSONObj {

    @JsonProperty(value = "headpose")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private HeadPose headPose;

    @JsonProperty(value = "gender")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String gender;

    @JsonProperty(value = "age")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer age;

    @JsonProperty(value = "dress")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Dress dress;

    @JsonProperty(value = "smile")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String smile;

    public HeadPose getHeadPose() {
        return headPose;
    }

    public void setHeadPose(HeadPose headPose) {
        this.headPose = headPose;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Dress getDress() {
        return dress;
    }

    public void setDress(Dress dress) {
        this.dress = dress;
    }

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }
}
