package com.huaweicloud.frs.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.result.BlurClassifyResult;
import com.huaweicloud.frs.client.result.FaceQualityResult;
import com.huaweicloud.frs.client.result.HeadPoseEstimateResult;
import com.huaweicloud.frs.common.FrsConstant;
import com.huaweicloud.frs.common.FrsException;
import com.huaweicloud.frs.common.ImageType;
import com.huaweicloud.frs.utils.HttpResponseUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Face quality service
 * Includes face quality, blur classify and head pose estimate
 */
public class QualityService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct face quality service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    QualityService(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    private FaceQualityResult faceQuality(String image, ImageType imageType) throws FrsException, IOException {
        String uri = String.format(FrsConstant.getFaceQualityUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image_base64", image);
        } else {
            json.put("image_url", image);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, FaceQualityResult.class);
    }

    /**
     * Face quality by base64
     *
     * @param imageBase64 base64 of image
     * @return Result of face quality
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public FaceQualityResult faceQualityByBase64(String imageBase64) throws FrsException, IOException {
        return faceQuality(imageBase64, ImageType.BASE64);
    }

    /**
     * Face quality by file path
     * Multipart not supported by server
     *
     * @param filePath File path of image
     * @return Result of face quality
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public FaceQualityResult faceQualityByFile(String filePath) throws FrsException, IOException {
        File file = new File(filePath);
        byte[] data = FileUtils.readFileToByteArray(file);
        String imageBase64 = Base64.encodeBase64String(data);
        return faceQuality(imageBase64, ImageType.BASE64);
    }

    /**
     * Face quality by obs url
     *
     * @param obsUrl Obs url of image
     * @return Result of face quality
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public FaceQualityResult faceQualityByObsUrl(String obsUrl) throws FrsException, IOException {
        return faceQuality(obsUrl, ImageType.OBSURL);
    }

    private BlurClassifyResult blurClassify(String image, ImageType imageType) throws FrsException, IOException {
        String uri = String.format(FrsConstant.getBlurClassifyUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image_base64", image);
        } else {
            json.put("image_url", image);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, BlurClassifyResult.class);
    }

    /**
     * Blur classify by base64
     *
     * @param imageBase64 base64 of image
     * @return Result of blur  classify
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public BlurClassifyResult blurClassifyByBase64(String imageBase64) throws FrsException, IOException {
        return blurClassify(imageBase64, ImageType.BASE64);
    }

    /**
     * Blur classify by file
     *
     * @param filePath File path of image
     * @return Result of blur  classify
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public BlurClassifyResult blurClassifyByFile(String filePath) throws FrsException, IOException {
        File file = new File(filePath);
        byte[] data = FileUtils.readFileToByteArray(file);
        String imageBase64 = Base64.encodeBase64String(data);
        return blurClassify(imageBase64, ImageType.BASE64);
    }

    /**
     * Blur classify by obs url
     *
     * @param obsUrl Obs url of image
     * @return Result of blur  classify
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public BlurClassifyResult blurClassifyByObsUrl(String obsUrl) throws FrsException, IOException {
        return blurClassify(obsUrl, ImageType.OBSURL);
    }

    private HeadPoseEstimateResult headPoseEstimate(String image, ImageType imageType) throws FrsException, IOException {
        String uri = String.format(FrsConstant.getHeadPoseEstimate(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image_base64", image);
        } else {
            json.put("image_url", image);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, HeadPoseEstimateResult.class);
    }

    /**
     * Estimate head pose by base64
     *
     * @param imageBase64 base64 of image
     * @return Result of head pose estimate
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public HeadPoseEstimateResult headPoseEstimateByBase64(String imageBase64) throws FrsException, IOException {
        return headPoseEstimate(imageBase64, ImageType.BASE64);
    }

    /**
     * Estimate head pose by file path
     *
     * @param filePath File path of image
     * @return Result of head pose estimate
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public HeadPoseEstimateResult headPoseEstimateByFile(String filePath) throws FrsException, IOException {
        File file = new File(filePath);
        byte[] data = FileUtils.readFileToByteArray(file);
        String imageBase64 = Base64.encodeBase64String(data);
        return headPoseEstimate(imageBase64, ImageType.BASE64);
    }

    /**
     * Estimate head pose by obs url
     *
     * @param obsUrl Obs url of image
     * @return Result of head pose estimate
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public HeadPoseEstimateResult headPoseEstimateByObsUrl(String obsUrl) throws FrsException, IOException {
        return headPoseEstimate(obsUrl, ImageType.OBSURL);
    }
}
