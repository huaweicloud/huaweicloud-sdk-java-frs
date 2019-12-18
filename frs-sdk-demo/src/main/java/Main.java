import com.huaweicloud.frs.client.service.FrsClient;
import com.huaweicloud.frs.client.param.AuthInfo;
import com.huaweicloud.frs.client.param.ProxyHostInfo;
import com.huaweicloud.frs.client.result.*;
import com.huaweicloud.frs.common.FrsException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        /**
         * ##########################sdk brief#########################
         *
         * com.huaweicloud.frs.client.service.FrsClient # Main class, should be initialized first
         * com.huaweicloud.frs.client.service.* # Correspond to rest api
         * com.huaweicloud.frs.client.result.* # Correspond to api response
         *
         * ##########################sdk brief#########################
         */
        //Demo for v1
        demoV1();
        //Demo for v2
        demoV2();
    }

    private static void demoV1() {

        //Step.1 Create frs client
        String ak = "ak";
        String sk = "sk";
        String endpoint = "https://face.cn-north-1.myhuaweicloud.com";
        String region = "cn-north-1";
        String projectId = "projectId";

        AuthInfo authInfo = new AuthInfo(endpoint, region, ak, sk);

        ProxyHostInfo proxyHostInfo = new ProxyHostInfo("127.0.0.1", 8080, "user name", "pwd");

        FrsClient frsClient = new FrsClient(authInfo, projectId/*, proxyHostInfo*/);

        //Step.2 Get service
        frsClient.getCompareService();
        frsClient.getDetectService();
        frsClient.getFaceService();
        frsClient.getFaceSetService();
        frsClient.getLiveDetectService();
        frsClient.getSearchService();

        //Step.3 User api

        //Create face set
        try {
            CreateFaceSetResult createFaceSetResult = frsClient.getFaceSetService().createFaceSet("faceSetName");
            //createFaceSetResult; //Http response
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add face
        try {
            AddFaceResult addFaceResult = frsClient.getFaceService().addFaceByFile("faceSetName", "data/image1.jpg");
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Delete face
        try {
            DeleteFaceResult deleteFaceResult = frsClient.getFaceService().deleteFaceByFaceId("faceSetName", "faceId");
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Face compare
        try {
            CompareFaceResult compareFaceResult = frsClient.getCompareService().compareFaceByFile("data/image2.jpeg", "data/image2.jpeg");
            //compareFaceResult; //Http response
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Face detect
        try {
            DetectFaceResult detectFaceResult = frsClient.getDetectService().detectFaceByObsUrl("data/image1.jpg");
            //detectFaceResult; //Http response
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Face search
        try {
            SearchFaceResult searchFaceResult = frsClient.getSearchService().searchFaceByObsUrl("faceSetName", "bucket/file.jpg");
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demoV2() {
        /**
         * ##########################sdk brief#########################
         *
         * com.huaweicloud.frs.client.service.FrsClient # Main class, should be initialized first
         * com.huaweicloud.frs.client.service.* # Correspond to rest api
         * com.huaweicloud.frs.client.result.* # Correspond to api response
         *
         * ##########################sdk brief#########################
         */

        //Step.1 Create frs client
        String ak = "ak";
        String sk = "sk";
        String endpoint = "https://face.cn-north-1.myhuaweicloud.com";
        String region = "cn-north-1";
        String projectId = "projectId";

        AuthInfo authInfo = new AuthInfo(endpoint, region, ak, sk);

        ProxyHostInfo proxyHostInfo = new ProxyHostInfo("127.0.0.1", 8080, "user name", "pwd");

        FrsClient frsClient = new FrsClient(authInfo, projectId/*, proxyHostInfo*/);

        //Step.2 Get v2 service
        frsClient.getV2().getCompareService();
        frsClient.getV2().getDetectService();
        frsClient.getV2().getFaceService();
        frsClient.getV2().getFaceSetService();
        frsClient.getV2().getSearchService();

        //Step.3 User api

        //Create face set
        try {
            CreateFaceSetResult createFaceSetResult = frsClient.getV2().getFaceSetService().createFaceSet("faceSetName");
            //createFaceSetResult; //Http response
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add face
        try {
            AddFaceResult addFaceResult = frsClient.getV2().getFaceService().addFaceByFile("faceSetName", "data/image1.jpg");
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Delete face
        try {
            DeleteFaceResult deleteFaceResult = frsClient.getV2().getFaceService().deleteFaceByFaceId("faceSetName", "faceId");
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Face compare
        try {
            CompareFaceResult compareFaceResult = frsClient.getV2().getCompareService().compareFaceByFile("data/image2.jpeg", "data/image2.jpeg");
            //compareFaceResult; //Http response
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Face detect
        try {
            DetectFaceResult detectFaceResult = frsClient.getV2().getDetectService().detectFaceByObsUrl("data/image1.jpg");
            //detectFaceResult; //Http response
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Face search
        try {
            SearchFaceResult searchFaceResult = frsClient.getV2().getSearchService().searchFaceByObsUrl("faceSetName", "bucket/file.jpg");
        } catch (FrsException e) { //While http status code is not http_ok
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
