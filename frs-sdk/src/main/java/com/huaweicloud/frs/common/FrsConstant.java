package com.huaweicloud.frs.common;

public class FrsConstant {
    private static final String faceDetectUri = "/v1/%s/face-detect";
    private static final String faceCompareUri = "/v1/%s/face-compare";
    private static final String faceSearchUri = "/v1/%s/face-sets/%s/search";
    private static final String faceAddUri = "/v1/%s/face-sets/%s/faces";
    private static final String faceGetRangeUri = "/v1/%s/face-sets/%s/faces?offset=%d&limit=%d";
    private static final String faceGetOneUri = "/v1/%s/face-sets/%s/faces?face_id=%s";
    private static final String faceDeleteByExternalImageIdUri = "/v1/%s/face-sets/%s/faces?external_image_id=%s";
    private static final String faceDeleteByFaceIdUri = "/v1/%s/face-sets/%s/faces?face_id=%s";
    private static final String faceDeleteByFieldIdUri = "/v1/%s/face-sets/%s/faces?%s=%s";
    private static final String faceSetCreateUri = "/v1/%s/face-sets";
    private static final String faceSetGetAllUri = "/v1/%s/face-sets";
    private static final String faceSetGetOneUri = "/v1/%s/face-sets/%s";
    private static final String faceSetDeleteUri = "/v1/%s/face-sets/%s";
    private static final String liveDetectUri = "/v1/%s/live-detect";
    private static final String faceQualityUri = "/v1/%s/face/quality/face-quality";
    private static final String blurClassifyUri = "/v1/%s/face/quality/blur-classify";
    private static final String headPoseEstimate = "/v1/%s/face/quality/head-pose-estimate";

    public static String getFaceDetectUri() {
        return faceDetectUri;
    }

    public static String getFaceCompareUri() {
        return faceCompareUri;
    }

    public static String getFaceSearchUri() {
        return faceSearchUri;
    }

    public static String getFaceAddUri() {
        return faceAddUri;
    }

    public static String getFaceGetRangeUri() {
        return faceGetRangeUri;
    }

    public static String getFaceGetOneUri() {
        return faceGetOneUri;
    }

    public static String getFaceDeleteByExternalImageIdUri() {
        return faceDeleteByExternalImageIdUri;
    }

    public static String getFaceDeleteByFieldIdUri() {
        return faceDeleteByFieldIdUri;
    }

    public static String getFaceDeleteByFaceIdUri() {
        return faceDeleteByFaceIdUri;
    }

    public static String getFaceSetCreateUri() {
        return faceSetCreateUri;
    }

    public static String getFaceSetGetAllUri() {
        return faceSetGetAllUri;
    }

    public static String getFaceSetGetOneUri() {
        return faceSetGetOneUri;
    }

    public static String getFaceSetDeleteUri() {
        return faceSetDeleteUri;
    }

    public static String getLiveDetectUri() {
        return liveDetectUri;
    }

    public static String getFaceQualityUri() {
        return faceQualityUri;
    }

    public static String getBlurClassifyUri() {
        return blurClassifyUri;
    }

    public static String getHeadPoseEstimate() {
        return headPoseEstimate;
    }
}
