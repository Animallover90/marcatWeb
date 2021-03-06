package marcat.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
@Slf4j
public class AwsS3 {

    // 서버에서 값을 가져오는 아래 주석의 메서드일 경우 아래 3가지의 값은 삭제 가능
    @Value("#{aws['cloud.aws.credentials.accessKey']}")
    private String accessKey;
    @Value("#{aws['cloud.aws.credentials.secretKey']}")
    private String secretKey;
    @Value("#{aws['cloud.aws.region.static']}")
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

//    @Bean
//    public AmazonS3Client amazonS3Client() {
//        return (AmazonS3Client) AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//    }
}
