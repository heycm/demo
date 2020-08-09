package online.heycm.minio.config;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-08-09 16:32
 */
@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String url;
    @Value("${minio.access}")
    private String access;
    @Value("${minio.secret}")
    private String secret;
    @Value("${minio.bucket}")
    private String bucket;


    @Bean
    public MinioClient minioClient() throws Exception {
        MinioClient minioClient = new MinioClient(url, access, secret);
        boolean bucketExists = minioClient.bucketExists(bucket);
        if (!bucketExists) {
            minioClient.makeBucket(bucket);
            minioClient.setBucketPolicy(bucket, "*", PolicyType.READ_WRITE);
        }
        return minioClient;
    }

}
