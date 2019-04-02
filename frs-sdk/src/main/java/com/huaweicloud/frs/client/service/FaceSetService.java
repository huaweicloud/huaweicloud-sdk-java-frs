package com.huaweicloud.frs.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaweicloud.frs.access.FrsAccess;
import com.huaweicloud.frs.client.param.CreateExternalFields;
import com.huaweicloud.frs.client.result.CreateFaceSetResult;
import com.huaweicloud.frs.client.result.DeleteFaceSetResult;
import com.huaweicloud.frs.client.result.GetAllFaceSetsResult;
import com.huaweicloud.frs.client.result.GetFaceSetResult;
import com.huaweicloud.frs.common.FrsConstant;
import com.huaweicloud.frs.common.FrsException;
import com.huaweicloud.frs.utils.HttpResponseUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Face set operation service
 * Includes create, get and delete operations
 */
public class FaceSetService {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private FrsAccess service;
    private String projectId;

    /**
     * Construct face set operation service, invoked by frs client
     *
     * @param service   frs access
     * @param projectId project id
     */
    FaceSetService(FrsAccess service, String projectId) {
        this.service = service;
        this.projectId = projectId;
    }

    /**
     * Create face set
     *
     * @param faceSetName          Face set name
     * @param faceSetCapacity      Face set capacity
     * @param createExternalFields External fields of face set
     * @return Result of create face set
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public CreateFaceSetResult createFaceSet(String faceSetName, Integer faceSetCapacity, CreateExternalFields createExternalFields) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceSetCreateUri(), this.projectId);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = new HashMap<>();
        json.put("face_set_name", faceSetName);
        if (null != faceSetCapacity) {
            json.put("face_set_capacity", faceSetCapacity);
        }
        if (null != createExternalFields) {
            json.put("external_fields", createExternalFields.getExternalFields());
        }
        RequestBody requestBody = RequestBody.create(JSON, mapper.writeValueAsString(json));
        Response httpResponse = this.service.post(uri, requestBody);
        return HttpResponseUtils.httpResponse2Result(httpResponse, CreateFaceSetResult.class);
    }

    /**
     * Create face set
     *
     * @param faceSetName     Face set name
     * @param faceSetCapacity Face set capacity
     * @return Result of create face set
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public CreateFaceSetResult createFaceSet(String faceSetName, int faceSetCapacity) throws FrsException, IOException {
        return createFaceSet(faceSetName, faceSetCapacity, null);
    }

    /**
     * Create face set
     *
     * @param faceSetName Face set name
     * @return Result of create face set
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public CreateFaceSetResult createFaceSet(String faceSetName) throws FrsException, IOException {
        return createFaceSet(faceSetName, null, null);
    }

    /**
     * Get all face sets
     *
     * @return Result of get all face sets
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public GetAllFaceSetsResult getAllFaceSets() throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceSetGetAllUri(), this.projectId);
        Response httpResponse = this.service.get(uri);
        return HttpResponseUtils.httpResponse2Result(httpResponse, GetAllFaceSetsResult.class);
    }

    /**
     * Get face set
     *
     * @param faceSetName Face set name
     * @return Result of get face
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public GetFaceSetResult getFaceSet(String faceSetName) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceSetGetOneUri(), this.projectId, faceSetName);
        Response httpResponse = this.service.get(uri);
        return HttpResponseUtils.httpResponse2Result(httpResponse, GetFaceSetResult.class);
    }

    /**
     * Delete face set
     *
     * @param faceSetName Face set name
     * @return Result of delete face set
     * @throws FrsException Throws while http status code is not 200
     * @throws IOException  IO exception
     */
    public DeleteFaceSetResult deleteFaceSet(String faceSetName) throws FrsException, IOException {
        String uri = String.format(FrsConstant.V1.getFaceSetDeleteUri(), this.projectId, faceSetName);
        Response httpResponse = this.service.delete(uri);
        return HttpResponseUtils.httpResponse2Result(httpResponse, DeleteFaceSetResult.class);
    }
}
