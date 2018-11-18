package com.huaweicloud.frs.client.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.result.DetectFaceResult;
import com.huaweicloud.frs.common.FrsConstant;
import com.huaweicloud.frs.common.FrsException;
import com.huaweicloud.frs.common.ImageType;
import com.huaweicloud.frs.utils.HttpResponseUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Face detect service.
 * Supports face detect with base64, file and obs url
 */
public class DetectService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct face detect service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    DetectService(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    private DetectFaceResult detectFace(String image, ImageType imageType, String attributes) throws FrsException, IOException {
        String uri = String.format(FrsConstant.getFaceDetectUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image_base64", image);
        } else {
            json.put("image_url", image);
        }

        if (null != attributes) {
            json.put("attributes", attributes);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, DetectFaceResult.class);
    }

    /**
     * Detect face by base64
     *
     * @param imageBase64 Base64 of image
     * @param attributes  Face detect attributes
     * @return Face detect result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DetectFaceResult detectFaceByBase64(String imageBase64, String attributes) throws FrsException, IOException {
        return this.detectFace(imageBase64, ImageType.BASE64, attributes);
    }

    /**
     * Detect face by base64
     *
     * @param imageBase64 Base64 of image
     * @return Face detect result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DetectFaceResult detectFaceByBase64(String imageBase64) throws FrsException, IOException {
        return this.detectFaceByBase64(imageBase64, null);
    }

    /**
     * Detect face by file path
     *
     * @param filePath   File path of image
     * @param attributes Face detect attributes
     * @return Face detect result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DetectFaceResult detectFaceByFile(String filePath, String attributes) throws FrsException, IOException {
        String uri = String.format(FrsConstant.getFaceDetectUri(), this.projectId);
        File image = new File(filePath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("application/octet-stream"), image);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("image_file", image.getName(), imageBody);
        if (attributes != null) {
            builder.addFormDataPart("attributes", attributes);
        }
        RequestBody requestBody = builder.build();
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, DetectFaceResult.class);
    }

    /**
     * Detect face by file path
     *
     * @param filePath File path of image
     * @return Face detect result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DetectFaceResult detectFaceByFile(String filePath) throws FrsException, IOException {
        return this.detectFaceByFile(filePath, null);
    }

    /**
     * Detect face by obs url
     *
     * @param obsUrl     Obs url of image
     * @param attributes Face detect attributes
     * @return Face detect result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DetectFaceResult detectFaceByObsUrl(String obsUrl, String attributes) throws FrsException, IOException {
        return this.detectFace(obsUrl, ImageType.OBSURL, attributes);
    }

    /**
     * Detect face by obs url
     *
     * @param obsUrl Obs url of image
     * @return Face detect result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DetectFaceResult detectFaceByObsUrl(String obsUrl) throws FrsException, IOException {
        return this.detectFaceByObsUrl(obsUrl, null);
    }
}
