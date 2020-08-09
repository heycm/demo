package online.heycm.minio.utils;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @Description Minio 工具类
 * @Author heycm@qq.com
 * @Date 2020-08-09 16:30
 */
@Component
public class MinioUtil {

    @Value("${minio.url}")
    private String url;
    @Value("${minio.bucket}")
    private String bucket;
    @Resource
    MinioClient minioClient;

    /**
     * 上传文件
     * @param in 文件输入流
     * @param fileName 文件名称
     * @param contentType 媒体类型
     * @return 文件地址
     */
    public String upload(InputStream in, String fileName, String contentType) {
        try {
            minioClient.putObject(bucket, fileName, in, contentType);
            return url + "/" + bucket + "/" + fileName;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 上传文件
     * @param in 文件输入流
     * @param fileName 文件名称
     * @return 文件地址
     */
    public String upload(InputStream in, String fileName) {
       return upload(in, fileName,"application/octet-stream");
    }

    /**
     * 检查文件是否存在
     * @param fileName 文件名称
     * @return true 存在 false 不存在
     */
    public boolean stat(String fileName) {
        try {
            minioClient.statObject(bucket, fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除文件
     * @param fileName 文件名
     * @return true 成功 false 失败
     */
    public boolean remove(String fileName) {
        if (!stat(fileName)) {
            return true;
        }
        try {
            minioClient.removeObject(bucket, fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
