package me.mason.demo.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * MinIO文件处理类
 *
 * @author: mason
 * @since: 2020/3/30
 **/
public class FileManager {

    /**
     * 上传文件
     *
     * @param objectName
     * @param filePath
     * @throws XmlPullParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public void uploadFile(MinioClient minioClient, String bucketName, String objectName, String filePath) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            // Check if the bucket already exists.
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist) {
                // Make a new bucket to hold file
                minioClient.makeBucket(bucketName);
            }
            // Upload file to the bucket with putObject
            minioClient.putObject(bucketName, objectName, filePath, null, null, null, null);
            System.out.println("put " + filePath + " to " + bucketName + ",OK");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 下载文件
     *
     * @param objectName
     * @param downloadPath
     * @throws XmlPullParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public void downloadFile(MinioClient minioClient, String bucketName, String objectName, String downloadPath) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        File file = new File(downloadPath);
        try (OutputStream out = new FileOutputStream(file)) {
            InputStream inputStream = minioClient.getObject(bucketName, objectName);
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            while ((byteread = inputStream.read(tempbytes)) != -1) {
                out.write(tempbytes, 0, byteread);
            }

            System.out.println("get " + objectName + " to " + downloadPath + ",OK");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 删除文件
     *
     * @param minioClient
     * @param objectName
     */
    public void deleteFile(MinioClient minioClient, String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        minioClient.removeObject(bucketName, objectName);
    }

    /**
     * 罗列文件
     * @param minioClient
     * @param bucketName
     * @throws XmlPullParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public void listFile(MinioClient minioClient, String bucketName) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(bucketName);
            Iterator<Result<Item>> iterator = results.iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next().get();
                System.out.println(item.objectName() + ", " + item.objectSize() + "B");
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 查看文件信息
     * @param minioClient
     * @param bucketName
     * @param objectName
     * @throws XmlPullParserException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public void getObjectStat(MinioClient minioClient, String bucketName, String objectName) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            ObjectStat objectStat = minioClient.statObject(bucketName, objectName);
            System.out.println(objectStat);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 检查文件是否存在MinIO
     *
     * @param minioClient
     * @param bucketName
     * @param objectName
     * @return
     */
    public boolean checkFileExist(MinioClient minioClient, String bucketName, String objectName) {
        boolean found = false;
        try {
            minioClient.statObject(bucketName, objectName);
            found = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return found;
    }
}
