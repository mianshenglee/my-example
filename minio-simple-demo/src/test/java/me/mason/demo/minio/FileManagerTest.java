package me.mason.demo.minio;

import com.google.common.io.Files;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * MinIO文件处理测试
 *
 * @author: mason
 * @since: 2020/3/30
 **/
class FileManagerTest {
    // minio信息
    public String endpoint = "http://192.168.11.249:9001";
    public String accessKey = "minio";
    public String secretKey = "minio123";
    public String bucketName = "image";

    //文件信息
    private String testPath = "C://";
    private String fileName = "demo.txt";
    private String downloadFileName = "test_download.txt";
    private String objectName = "minio_test.txt";
    private String filePath = testPath + fileName;
    private String charset = "utf-8";
    private String fileContent = "demo file content for minio";

    private FileManager fileManager;
    private MinioClient minioClient;

    @BeforeEach
    void setUp() throws IOException, InvalidPortException, InvalidEndpointException {
        //创建文件，写入内容
        try (BufferedWriter bufferedWriter = Files.newWriter(new File(filePath), Charset.forName(charset))) {
            bufferedWriter.write(fileContent);
        }
        fileManager = new FileManager();
        minioClient = new MinioClient(endpoint, accessKey, secretKey);
    }

    @AfterEach
    void tearDown() {
        //处理测试文件
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
        //处理下载文件
        File downloadFile = new File(testPath + downloadFileName);
        if (downloadFile.exists()){
            downloadFile.delete();
        }
    }

    @Test
    void testUploadFile() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException{
        fileManager.uploadFile(minioClient,bucketName,objectName, filePath);
        assertTrue(fileManager.checkFileExist(minioClient,bucketName,objectName));
    }

    @Test
    void testDownloadFile() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        fileManager.downloadFile(minioClient,bucketName,objectName,testPath+downloadFileName);
        File file = new File(testPath + downloadFileName);
        assertTrue(file.exists());
    }

    @Test
    void testDeleteFile() throws IOException, XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, InvalidArgumentException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InternalException {
        fileManager.deleteFile(minioClient,bucketName,objectName);
        assertFalse(fileManager.checkFileExist(minioClient,bucketName,objectName));
    }

    @Test
    void testListFile() throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        fileManager.listFile(minioClient,bucketName);
    }
}