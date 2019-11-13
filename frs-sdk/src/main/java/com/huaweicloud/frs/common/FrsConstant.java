package com.huaweicloud.frs.common;

public class FrsConstant {
    public static class V1 {
        private static final String faceDetectUri = "/v1/%s/face-detect";
        private static final String faceCompareUri = "/v1/%s/face-compare";
        private static final String faceSearchUri = "/v1/%s/face-sets/%s/search";
        private static final String faceAddUri = "/v1/%s/face-sets/%s/faces";
        private static final String faceUpdateUri = "/v1/%s/face-sets/%s/faces";
        private static final String faceGetRangeUri = "/v1/%s/face-sets/%s/faces?offset=%d&limit=%d";
        private static final String faceGetOneUri = "/v1/%s/face-sets/%s/faces?face_id=%s";
        private static final String faceDeleteByExternalImageIdUri = "/v1/%s/face-sets/%s/faces?external_image_id=%s";
        private static final String faceDeleteByFaceIdUri = "/v1/%s/face-sets/%s/faces?face_id=%s";
        private static final String faceDeleteByFieldIdUri = "/v1/%s/face-sets/%s/faces?%s=%s";
        private static final String faceDeleteByBatchUri = "/v1/%s/face-sets/%s/faces/batch";
        private static final String faceSetCreateUri = "/v1/%s/face-sets";
        private static final String faceSetGetAllUri = "/v1/%s/face-sets";
        private static final String faceSetGetOneUri = "/v1/%s/face-sets/%s";
        private static final String faceSetDeleteUri = "/v1/%s/face-sets/%s";
        private static final String liveDetectUri = "/v1/%s/live-detect";

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

        public static String getFaceUpdateUri() {
            return faceUpdateUri;
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

        public static String getFaceDeleteByBatchUri() {
            return faceDeleteByBatchUri;
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

    }

    public static class V2 {
        private static final String faceDetectUri = "/v2/%s/face-detect";
        private static final String faceCompareUri = "/v2/%s/face-compare";
        private static final String faceSearchUri = "/v2/%s/face-sets/%s/search";
        private static final String faceAddUri = "/v2/%s/face-sets/%s/faces";
        private static final String faceUpdateUri = "/v2/%s/face-sets/%s/faces";
        private static final String faceGetRangeUri = "/v2/%s/face-sets/%s/faces?offset=%d&limit=%d";
        private static final String faceGetOneUri = "/v2/%s/face-sets/%s/faces?face_id=%s";
        private static final String faceDeleteByExternalImageIdUri = "/v2/%s/face-sets/%s/faces?external_image_id=%s";
        private static final String faceDeleteByFaceIdUri = "/v2/%s/face-sets/%s/faces?face_id=%s";
        private static final String faceDeleteByFieldIdUri = "/v2/%s/face-sets/%s/faces?%s=%s";
        private static final String faceDeleteByBatchUri = "/v2/%s/face-sets/%s/faces/batch";
        private static final String faceSetCreateUri = "/v2/%s/face-sets";
        private static final String faceSetGetAllUri = "/v2/%s/face-sets";
        private static final String faceSetGetOneUri = "/v2/%s/face-sets/%s";
        private static final String faceSetDeleteUri = "/v2/%s/face-sets/%s";


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

        public static String getFaceUpdateUri() {
            return faceUpdateUri;
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

        public static String getFaceDeleteByBatchUri() {
            return faceDeleteByBatchUri;
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

    }

}
