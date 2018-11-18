package com.huaweicloud.frs.common;

public class FrsException extends Exception {
    public FrsException() {
        super();
    }

    public FrsException(int httpStatusCode, String msg) {
        super(String.format("Face Recognition Service returns wrong response. HttpStatusCode: %d, Details: %s", httpStatusCode, msg));
    }
}
