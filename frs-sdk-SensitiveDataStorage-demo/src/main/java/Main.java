import com.frs.demo.dao.IFaceDao;
import com.frs.demo.pojo.Face;
import com.frs.demo.utils.MybatisUtil;
import com.huaweicloud.frs.client.param.*;
import com.huaweicloud.frs.client.result.AddFaceResult;
import com.huaweicloud.frs.client.result.CreateFaceSetResult;
import com.huaweicloud.frs.client.result.DeleteFaceSetResult;
import com.huaweicloud.frs.client.result.UpdateFaceResult;
import com.huaweicloud.frs.client.service.FaceService;
import com.huaweicloud.frs.client.service.FaceSetService;
import com.huaweicloud.frs.client.service.FrsClient;
import com.huaweicloud.frs.common.FrsException;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        demo();

        //demoDeleteFaceSet();

    }

    private static void demo(){
        String ak = "ak";
        String sk = "sk";
        String endpoint = "https://face.cn-north-4.myhuaweicloud.com";
        String region = "cn-north-4";
        String projectId = "projectId";

        AuthInfo authInfo = new AuthInfo(endpoint, region, ak, sk);

        ProxyHostInfo proxyHostInfo = new ProxyHostInfo("127.0.0.1", 8080, "user name", "pwd");

        FrsClient frsClient = new FrsClient(authInfo, projectId/*, proxyHostInfo*/);

        //获取人脸库服务
        FaceSetService faceSetService = frsClient.getFaceSetService();
        //获取人脸资源服务
        FaceService faceService = frsClient.getFaceService();

        //创建人脸库
        try{
            //原SDK使用自定义字段创建人脸库
            //CreateExternalFields createExternalFields = new CreateExternalFields();
            //createExternalFields.addField("user_name", FieldType.STRING);
            //createExternalFields.addField("id_card",FieldType.STRING);
            //createExternalFields.addField("age",FieldType.INTEGER);
            //CreateFaceSetResult createFaceSetResult = faceSetService.createFaceSet("face_set_name",10000,createExternalFields);

            //现创建人脸库
            CreateFaceSetResult createFaceSetResult = faceSetService.createFaceSet("face_set_name",10000);
        }catch (FrsException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        //添加人脸
        try{
            //原SDK使用自定义字段添加人脸
            //AddExternalFields addExternalFields = new AddExternalFields();
            //addExternalFields.addField("user_name","username");
            //addExternalFields.addField("id_card","61010xxxxxxxxxxx");
            //addExternalFields.addField("age",20);
            //AddFaceResult addFaceResult = faceService.addFaceByFile("face_set_name","/data/image1.jpg",addExternalFields);

            //现添加人脸
            AddFaceResult addFaceResult = faceService.addFaceByFile("face_set_name","/data/image1.jpg");
            //获取faceId
            String faceId = addFaceResult.getFaces().get(0).getFaceId();
            //添加数据进数据库
            SqlSession sqlSession = MybatisUtil.openSqlSession();
            IFaceDao faceDao =sqlSession.getMapper(IFaceDao.class);
            //示例中使用mybatis向数据库添加人脸的敏感数据
            String userName = "username";
            String idCard = "61010xxxxxxxxxxx";
            int age = 20;

            Face face = new Face(faceId,userName,idCard,age);
            faceDao.insertFace(face);
            sqlSession.commit();
            sqlSession.close();

        }catch (FrsException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void demoDeleteFaceSet(){
        String ak = "ak";
        String sk = "sk";
        String endpoint = "https://face.cn-north-4.myhuaweicloud.com";
        String region = "cn-north-4";
        String projectId = "projectId";

        AuthInfo authInfo = new AuthInfo(endpoint, region, ak, sk);

        ProxyHostInfo proxyHostInfo = new ProxyHostInfo("127.0.0.1", 8080, "user name", "pwd");

        FrsClient frsClient = new FrsClient(authInfo, projectId/*, proxyHostInfo*/);

        FaceSetService faceSetService = frsClient.getFaceSetService();

        try{
            DeleteFaceSetResult deleteFaceSetResult = faceSetService.deleteFaceSet("face_set_name");
            System.out.println(deleteFaceSetResult.getFaceSetName());
        }catch (FrsException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
