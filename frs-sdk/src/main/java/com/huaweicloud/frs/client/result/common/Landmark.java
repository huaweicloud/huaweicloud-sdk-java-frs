package com.huaweicloud.frs.client.result.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huaweicloud.frs.common.JSONObj;

public class Landmark extends JSONObj {

    @JsonProperty(value = "eyes_contour")
    private PointList eyesContour;

    @JsonProperty(value = "mouth_contour")
    private PointList mouthContour;

    @JsonProperty(value = "face_contour")
    private PointList faceContour;

    @JsonProperty(value = "eyebrow_contour")
    private PointList eyebrowContour;

    @JsonProperty(value = "nose_contour")
    private PointList noseContour;


    public PointList getEyesContour() {
        return eyesContour;
    }

    public void setEyesContour(PointList eyesContour) {
        this.eyesContour = eyesContour;
    }

    public PointList getMouthContour() {
        return mouthContour;
    }

    public void setMouthContour(PointList mouthContour) {
        this.mouthContour = mouthContour;
    }

    public PointList getFaceContour() {
        return faceContour;
    }

    public void setFaceContour(PointList faceContour) {
        this.faceContour = faceContour;
    }

    public PointList getEyebrowContour() {
        return eyebrowContour;
    }

    public void setEyebrowContour(PointList eyebrowContour) {
        this.eyebrowContour = eyebrowContour;
    }

    public PointList getNoseContour() {
        return noseContour;
    }

    public void setNoseContour(PointList noseContour) {
        this.noseContour = noseContour;
    }
}
