package com.huaweicloud.frs.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.result.CompareFaceResult;
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
 * Face compare service.
 * Supports compare with base64, file and obs url
 */
public class CompareService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct compare service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    CompareService(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    private CompareFaceResult compareFace(String image1, String image2, ImageType imageType) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceCompareUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image1_base64", image1);
            json.put("image2_base64", image2);
        } else {
            json.put("image1_url", image1);
            json.put("image2_url", image2);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, CompareFaceResult.class);
    }

    /**
     * Compare face by base64
     *
     * @param image1Base64 Base64 of image1
     * @param image2Base64 Base64 of image2
     * @return Face compare result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public CompareFaceResult compareFaceByBase64(String image1Base64, String image2Base64) throws FrsException, IOException {
        return this.compareFace(image1Base64, image2Base64, ImageType.BASE64);
    }

    /**
     * Compare face by file path
     *
     * @param filePath1 File path of image1
     * @param filePath2 File path of image2
     * @return Face compare result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public CompareFaceResult compareFaceByFile(String filePath1, String filePath2) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceCompareUri(), this.projectId);
        File image1 = new File(filePath1);
        File image2 = new File(filePath2);
        RequestBody image1Body = RequestBody.create(MediaType.parse("application/octet-stream"), image1);
        RequestBody image2Body = RequestBody.create(MediaType.parse("application/octet-stream"), image2);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image1_file", image1.getName(), image1Body)
                .addFormDataPart("image2_file", image2.getName(), image2Body)
                .build();
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, CompareFaceResult.class);
    }

    /**
     * Compare face by file path
     *
     * @param obsUrl1 Obs url of image1
     * @param obsUrl2 Obs url of image2
     * @return Face compare result
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public CompareFaceResult compareFaceByObsUrl(String obsUrl1, String obsUrl2) throws FrsException, IOException {
        return this.compareFace(obsUrl1, obsUrl2, ImageType.OBSURL);
    }
}
