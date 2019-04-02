package com.huaweicloud.frs.client.service.v2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.result.LiveDetectResult;
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
 * Live detect service
 * Supports live detect with base64, file and obs url
 */
public class LiveDetectServiceV2 {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct live detect service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    LiveDetectServiceV2(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    private LiveDetectResult liveDetect(String video, ImageType videoType, String actions, String actionTime) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V2.getLiveDetectUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == videoType) {
            json.put("video_base64", video);
        } else {
            json.put("video_url", video);
        }
        json.put("actions", actions);
        if (null != actionTime) {
            json.put("action_time", actionTime);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, LiveDetectResult.class);
    }

    /**
     * Live detect by base64
     *
     * @param videoBase64 Base64 of video
     * @param actions     Actions
     * @param actionTime  Action time
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectResult liveDetectByBase64(String videoBase64, String actions, String actionTime) throws FrsException, IOException {
        return liveDetect(videoBase64, ImageType.BASE64, actions, actionTime);
    }

    /**
     * Live detect by base64
     *
     * @param videoBase64 Base64 of video
     * @param actions     Actions
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectResult liveDetectByBase64(String videoBase64, String actions) throws FrsException, IOException {
        return liveDetectByBase64(videoBase64, actions, null);
    }

    /**
     * Live detect by file path
     *
     * @param videoPath  file path of video
     * @param actions    Actions
     * @param actionTime Action time
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectResult liveDetectByFile(String videoPath, String actions, String actionTime) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V2.getLiveDetectUri(), this.projectId);
        File video = new File(videoPath);
        RequestBody videoBody = RequestBody.create(MediaType.parse("application/octet-stream"), video);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("video_file", video.getName(), videoBody)
                .addFormDataPart("actions", actions);
        if (actionTime != null) {
            builder.addFormDataPart("action_time", actionTime);
        }
        RequestBody requestBody = builder.build();
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, LiveDetectResult.class);
    }

    /**
     * Live detect by file path
     *
     * @param videoPath File path of video
     * @param actions   Actions
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectResult liveDetectByFile(String videoPath, String actions) throws FrsException, IOException {
        return liveDetectByFile(videoPath, actions, null);
    }

    /**
     * Live detect by obs url
     *
     * @param videoUrl   Obs url of video
     * @param actions    Actions
     * @param actionTime Action time
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectResult liveDetectByObsUrl(String videoUrl, String actions, String actionTime) throws FrsException, IOException {
        return liveDetect(videoUrl, ImageType.OBSURL, actions, actionTime);
    }

    /**
     * Live detect by obs url
     *
     * @param videoUrl Obs url of video
     * @param actions  Actions
     * @return Result of live detect
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public LiveDetectResult liveDetectByObsUrl(String videoUrl, String actions) throws FrsException, IOException {
        return liveDetectByObsUrl(videoUrl, actions, null);
    }
}
