package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Face extends SimpleFace {

    @JsonProperty(value = "external_image_id")
    protected String externalImageId;

    @JsonProperty(value = "face_id")
    protected String faceId;

    @JsonProperty(value = "external_fields")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    protected Map<String, Object> externalFields;

    public Face() {
    }

    public Map<String, Object> getExternalFields() {
        return externalFields;
    }

    public void setExternalFields(Map<String, Object> externalFields) {
        this.externalFields = externalFields;
    }

    public String getExternalImageId() {
        return this.externalImageId;
    }

    public void setExternalImageId(String externalImageId) {
        this.externalImageId = externalImageId;
    }

    public String getFaceId() {
        return this.faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String toString() {
        return String.format("{\"externalFields\":%s,\"boundingBox\":%s,\"externalImageId\":\"%s\",\"faceId\":\"%s\"}",
                this.externalFields, this.boundingBox.toString(), this.externalImageId, this.faceId);
    }

}
