import com.huaweicloud.frs.client.param.AuthInfo;
import com.huaweicloud.frs.client.result.DetectFaceResult;
import com.huaweicloud.frs.client.result.SearchFaceResult;
import com.huaweicloud.frs.client.result.common.BoundingBox;
import com.huaweicloud.frs.client.result.common.DetectFace;
import com.huaweicloud.frs.client.service.FrsClient;
import com.huaweicloud.frs.common.FrsException;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        //Info
        String endpoint = "https://face.cn-north-1.myhuaweicloud.com";
        String region = "cn-north-1";
        String ak = "";
        String sk = "";
        String projectId = "";
        String imagePath = "data/test.jpg";
        //Init frs client
        AuthInfo authInfo = new AuthInfo(endpoint, region, ak, sk);
        FrsClient frsClient = new FrsClient(authInfo, projectId);
        //Load image file as byte array
        byte[] imageFile = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(imagePath);
            imageFile = fileInputStream.readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //Get all face image in origin image file
        List<byte[]> facesData = getAllFace(frsClient, imageFile);
        //Then you can search each face in your face set; note: code below just one example for search
        for (byte[] face : facesData) {
            String faceBase64 = java.util.Base64.getEncoder().encodeToString(face);
            try {
                SearchFaceResult searchFaceResult = frsClient.getSearchService().searchFaceByBase64("face-set-name", faceBase64);
                System.out.println(searchFaceResult.toJSONString());
            } catch (FrsException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param frsClient frs client in frs-sdk
     * @param imageFile image file in byte array
     * @return each item in list is face jpg file, can be used in search
     */
    public static List<byte[]> getAllFace(FrsClient frsClient, byte[] imageFile) {
        try {
            String imageBase64 = Base64.encodeBase64String(imageFile);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageFile);
            BufferedImage image = ImageIO.read(inputStream);
            //Get all face position in origin image
            DetectFaceResult detectFaceResult = frsClient.getDetectService().detectFaceByBase64(imageBase64);
            List<DetectFace> faces = detectFaceResult.getFaces();
            List<byte[]> facesData = new ArrayList<byte[]>();
            BufferedImage subImage;
            int i = 0;
            //Get sub image in origin image
            for (DetectFace detectFace : faces) {
                BoundingBox boundingBox = detectFace.getBoundingBox();
                subImage = image.getSubimage(boundingBox.getTopLeftX(), boundingBox.getTopLeftY(), boundingBox.getWidth(), boundingBox.getHeight());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(subImage, "jpg", outputStream);
                facesData.add(outputStream.toByteArray());
                outputStream.close();
                //test output
                FileOutputStream fileOutputStream = new FileOutputStream(String.format("data/%d.jpg", ++i));
                ImageIO.write(subImage, "jpg", fileOutputStream);
                fileOutputStream.close();
            }
            return facesData;
        } catch (FrsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
