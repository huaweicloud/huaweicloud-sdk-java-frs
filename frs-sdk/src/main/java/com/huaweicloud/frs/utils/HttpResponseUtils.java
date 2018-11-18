package com.huaweicloud.frs.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.common.FrsException;
import okhttp3.Response;

import java.io.IOException;

public class HttpResponseUtils {
    public static <T> T httpResponse2Result(Response httpResponse, Class<T> clazz) throws FrsException, IOException {
        if (null == httpResponse) {
            return null;
        }
        int statusCode = httpResponse.code();
        if (httpResponse.body() == null) {
            throw new IOException("Http response body is null");
        }
        String content = httpResponse.body().string();
        if (200 != statusCode) {
            throw new FrsException(statusCode, content);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, clazz);
        }
    }
}
