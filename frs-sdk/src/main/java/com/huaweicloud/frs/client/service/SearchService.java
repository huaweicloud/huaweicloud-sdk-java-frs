package com.huaweicloud.frs.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.param.SearchReturnFields;
import com.huaweicloud.frs.client.param.SearchSort;
import com.huaweicloud.frs.client.result.SearchFaceResult;
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
 * Face search service
 * Supports search with base64, file path, obs url and face id
 */
public class SearchService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct face search service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    SearchService(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    private SearchFaceResult searchFace(String faceSetName, String image, Integer topN, Double threshold, ImageType imageType,
                                        SearchSort searchSort, SearchReturnFields searchReturnFields, String filter) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceSearchUri(), this.projectId, faceSetName);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        if (ImageType.BASE64 == imageType) {
            json.put("image_base64", image);
        } else if (ImageType.FACEID == imageType) {
            json.put("face_id", image);
        } else {
            json.put("image_url", image);
        }
        if (null != topN) {
            json.put("top_n", topN);
        }
        if (null != threshold) {
            json.put("threshold", threshold);
        }
        if (null != searchSort) {
            json.put("sort", searchSort.getSearchSort());
        }
        if (null != searchReturnFields) {
            json.put("return_fields", searchReturnFields.getReturnFields());
        }
        if (null != filter) {
            json.put("filter", filter);
        }

        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, SearchFaceResult.class);
    }

    /**
     * Search face by base64
     *
     * @param faceSetName        Face set name
     * @param imageBase64        base64 of image
     * @param topN               Top n of image
     * @param threshold          Threshold of similarity
     * @param searchSort         Sort result
     * @param searchReturnFields Fields return
     * @param filter             Filter
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByBase64(String faceSetName, String imageBase64, Integer topN, Double threshold,
                                               SearchSort searchSort, SearchReturnFields searchReturnFields, String filter) throws FrsException, IOException {
        return searchFace(faceSetName, imageBase64, topN, threshold, ImageType.BASE64, searchSort, searchReturnFields, filter);
    }

    /**
     * Search face by base64
     *
     * @param faceSetName Face set name
     * @param imageBase64 base64 of image
     * @param topN        Top n of image
     * @param threshold   Threshold of similarity
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByBase64(String faceSetName, String imageBase64, Integer topN, Double threshold) throws FrsException, IOException {
        return searchFaceByBase64(faceSetName, imageBase64, topN, threshold, null, null, null);
    }

    /**
     * Search face by base64
     *
     * @param faceSetName Face set name
     * @param imageBase64 base64 of image
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByBase64(String faceSetName, String imageBase64) throws FrsException, IOException {
        return searchFaceByBase64(faceSetName, imageBase64, null, null, null, null, null);
    }

    /**
     * Search face by file path
     *
     * @param faceSetName        Face set name
     * @param filePath           file path of image
     * @param topN               Top n of image
     * @param threshold          Threshold of similarity
     * @param searchSort         Sort result
     * @param searchReturnFields Fields return
     * @param filter             Filter
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByFile(String faceSetName, String filePath, Integer topN, Double threshold,
                                             SearchSort searchSort, SearchReturnFields searchReturnFields, String filter) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceSearchUri(), this.projectId, faceSetName);
        ObjectMapper mapper = new ObjectMapper();
        File image = new File(filePath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("application/octet-stream"), image);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("image_file", image.getName(), imageBody);
        if (null != topN) {
            builder.addFormDataPart("top_n", String.valueOf(topN));
        }
        if (null != threshold) {
            builder.addFormDataPart("threshold", String.valueOf(threshold));
        }
        if (null != searchSort) {
            builder.addFormDataPart("sort", mapper.writeValueAsString(searchSort.getSearchSort()));
        }
        if (null != searchReturnFields) {
            builder.addFormDataPart("return_fields", mapper.writeValueAsString(searchReturnFields.getReturnFields()));
        }
        if (null != filter) {
            builder.addFormDataPart("filter", filter);
        }
        RequestBody requestBody = builder.build();
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, SearchFaceResult.class);
    }

    /**
     * Search face by file path
     *
     * @param faceSetName Face set name
     * @param filePath    file path of image
     * @param topN        Top n of image
     * @param threshold   Threshold of similarity
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByFile(String faceSetName, String filePath, Integer topN, Double threshold) throws FrsException, IOException {
        return this.searchFaceByFile(faceSetName, filePath, topN, threshold, null, null, null);
    }

    /**
     * Search face by file path
     *
     * @param faceSetName Face set name
     * @param filePath    file path of image
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByFile(String faceSetName, String filePath) throws FrsException, IOException {
        return this.searchFaceByFile(faceSetName, filePath, null, null, null, null, null);
    }

    /**
     * Search face by obs url
     *
     * @param faceSetName        Face set name
     * @param obsUrl             Obs url of image
     * @param topN               Top n of image
     * @param threshold          Threshold of similarity
     * @param searchSort         Sort result
     * @param searchReturnFields Fields return
     * @param filter             Filter
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByObsUrl(String faceSetName, String obsUrl, Integer topN, Double threshold,
                                               SearchSort searchSort, SearchReturnFields searchReturnFields, String filter) throws FrsException, IOException {
        return this.searchFace(faceSetName, obsUrl, topN, threshold, ImageType.OBSURL, searchSort, searchReturnFields, filter);
    }

    /**
     * Search face by obs url
     *
     * @param faceSetName Face set name
     * @param obsUrl      Obs url of image
     * @param topN        Top n of image
     * @param threshold   Threshold of similarity
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByObsUrl(String faceSetName, String obsUrl, Integer topN, Double threshold) throws FrsException, IOException {
        return this.searchFaceByObsUrl(faceSetName, obsUrl, topN, threshold, null, null, null);
    }

    /**
     * Search face by obs url
     *
     * @param faceSetName Face set name
     * @param obsUrl      Obs url of image
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByObsUrl(String faceSetName, String obsUrl) throws FrsException, IOException {
        return this.searchFaceByObsUrl(faceSetName, obsUrl, null, null, null, null, null);
    }

    /**
     * Search face by face id
     *
     * @param faceSetName        Face set name
     * @param faceId             Face id
     * @param topN               Top n of image
     * @param threshold          Threshold of similarity
     * @param searchSort         Sort result
     * @param searchReturnFields Fields return
     * @param filter             Filter
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByFaceId(String faceSetName, String faceId, Integer topN, Double threshold,
                                               SearchSort searchSort, SearchReturnFields searchReturnFields, String filter) throws FrsException, IOException {
        return searchFace(faceSetName, faceId, topN, threshold, ImageType.FACEID, searchSort, searchReturnFields, filter);
    }

    /**
     * Search face by face id
     *
     * @param faceSetName Face set name
     * @param faceId      Face id
     * @param topN        Top n of image
     * @param threshold   Threshold of similarity
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByFaceId(String faceSetName, String faceId, Integer topN, Double threshold) throws FrsException, IOException {
        return searchFaceByFaceId(faceSetName, faceId, topN, threshold, null, null, null);
    }

    /**
     * Search face by face id
     *
     * @param faceSetName Face set name
     * @param faceId      Face id
     * @return Result of face search
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public SearchFaceResult searchFaceByFaceId(String faceSetName, String faceId) throws FrsException, IOException {
        return searchFaceByFaceId(faceSetName, faceId, null, null, null, null, null);
    }
}
