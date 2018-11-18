package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

import java.util.Map;

public class FaceSet extends JSONObj {

    @JsonProperty(value = "face_number")
    private int faceNumber;

    @JsonProperty(value = "face_set_id")
    private String faceSetId;

    @JsonProperty(value = "face_set_name")
    private String faceSetName;

    @JsonProperty(value = "create_date")
    private String createDate;

    @JsonProperty(value = "face_set_capacity")
    private int faceSetCapacity;

    @JsonProperty(value = "external_fields")
    private Map<String, FieldType> externalFields;

    public FaceSet() {
    }

    public int getFaceNumber() {
        return this.faceNumber;
    }

    public void setFaceNumber(int faceNumber) {
        this.faceNumber = faceNumber;
    }

    public String getFaceSetId() {
        return this.faceSetId;
    }

    public void setFaceSetId(String faceSetId) {
        this.faceSetId = faceSetId;
    }

    public String getFaceSetName() {
        return this.faceSetName;
    }

    public void setFaceSetName(String faceSetName) {
        this.faceSetName = faceSetName;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getFaceSetCapacity() {
        return this.faceSetCapacity;
    }

    public void setFaceSetCapacity(int faceSetCapacity) {
        this.faceSetCapacity = faceSetCapacity;
    }

    public Map<String, FieldType> getExternalFields() {
        return externalFields;
    }

    public void setExternalFields(Map<String, FieldType> externalFields) {
        this.externalFields = externalFields;
    }

    public String toString() {
        if (null == faceSetId) {
            return "{}";
        }
        return String.format("{\"faceNumber\":%d,\"faceSetId\":\"%s\",\"faceSetName\":\"%s\",\"createDate\":\"%s\",\"faceSetCapacity\":%d,\"externalFields\":%s}",
                this.faceNumber, this.faceSetId, this.faceSetName, this.createDate, this.faceSetCapacity, this.externalFields);
    }

}
