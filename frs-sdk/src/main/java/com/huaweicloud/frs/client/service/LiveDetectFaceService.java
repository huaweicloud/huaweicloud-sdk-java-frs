package com.huaweicloud.frs.client.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.result.LiveDetectFaceResult;
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
 * Live detect face service (face liveness)
 * Supports live detect face with base64, file and obs url
 */
public class LiveDetectFaceService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct live detect face service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    LiveDetectFaceService(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    private LiveDetectFaceResult liveDetectFace(String image, ImageType imageType) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getLiveDetectFaceUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image_base64", image);
        } else {
            json.put("image_url", image);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody, this.projectId);
        return HttpResponseUtils.httpResponse2Result(httpResponse, LiveDetectFaceResult.class);
    }

    /**
     * Live detect face by base64
     *
     * @param imageBase64 Base64 of image
     * @return Result of live detect face
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectFaceResult liveDetectFaceByBase64(String imageBase64) throws FrsException, IOException {
        return liveDetectFace(imageBase64, ImageType.BASE64);
    }


    /**
     * Live detect face by file path
     *
     * @param imagePath  file path of image
     * @return Result of live detect face
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectFaceResult liveDetectResultByFile(String imagePath) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getLiveDetectFaceUri(), this.projectId);
        File image = new File(imagePath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("application/octet-stream"), image);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("image_file", image.getName(), imageBody);
        RequestBody requestBody = builder.build();
        Response httpResponse = this.service.post(uri, requestBody, this.projectId);
        return HttpResponseUtils.httpResponse2Result(httpResponse, LiveDetectFaceResult.class);
    }

    /**
     * Live detect face by file path
     *
     * @param imagePath File path of image
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectFaceResult liveDetectFaceByFile(String imagePath) throws FrsException, IOException {
        return liveDetectResultByFile(imagePath);
    }

    /**
     * Live detect face by obs url
     *
     * @param imageUrl   Obs url of image
     * @return Result of live detect face
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectFaceResult liveDetectByObsUrl(String imageUrl) throws FrsException, IOException {
        return liveDetectFace(imageUrl, ImageType.OBSURL);
    }

    /**
     * Live detect face by obs url
     *
     * @param imageUrl Obs url of image
     * @return Result of live detect face
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectFaceResult liveDetectFaceByObsUrl(String imageUrl) throws FrsException, IOException {
        return liveDetectByObsUrl(imageUrl);
    }
}
