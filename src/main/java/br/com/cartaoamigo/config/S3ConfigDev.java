package br.com.cartaoamigo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Profile("dev")
@Configuration
public class S3ConfigDev {
	
	@Value("${aws.access_key_id}")
	private String awsId;

	@Value("${aws.secret_access_key}")
	private String awsKey;

	@Value("${s3.region}")
	private String region;
	
	@Bean
	public AmazonS3Client s3client() {
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3Client s3client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
				                                                        .withRegion(Regions.fromName(region))
							                                            .withCredentials(new AWSStaticCredentialsProvider(awsCred))
							                                            .build();
		return s3client;
	}
}