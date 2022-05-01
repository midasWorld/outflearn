package com.midas.outflearn.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.midas.outflearn.aws.S3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AmazonS3 amazonS3Client(AwsConfig awsConfig) {
        return AmazonS3ClientBuilder.standard()
            .withRegion(Regions.fromName(awsConfig.getRegion()))
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(
                        awsConfig.getAccessKey(),
                        awsConfig.getSecretKey()
                    )
                )
            )
            .build();
    }

    @Bean
    public S3Client s3Client(AmazonS3 amazonS3, AwsConfig awsConfig) {
        return new S3Client(amazonS3, awsConfig.getUrl(), awsConfig.getBucketName());
    }
}
