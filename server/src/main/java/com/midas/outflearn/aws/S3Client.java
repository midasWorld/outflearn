package com.midas.outflearn.aws;

import java.io.InputStream;
import java.util.Map;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public final class S3Client {

	private final AmazonS3 amazonS3;
	private final String url;
	private final String bucketName;

	public S3Client(AmazonS3 amazonS3, String url, String bucketName) {
		this.amazonS3 = amazonS3;
		this.url = url;
		this.bucketName = bucketName;
	}

	public String upload(InputStream inputStream, long length, String key, String contentType,
		Map<String, String> metadata) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(length);
		objectMetadata.setContentType(contentType);
		if (metadata != null && !metadata.isEmpty()) {
			objectMetadata.setUserMetadata(metadata);
		}

		PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
		return executePut(request);
	}

	private String executePut(PutObjectRequest request) {
		amazonS3.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
		StringBuilder sb = new StringBuilder(url);
		if (!url.endsWith("/"))
			sb.append("/");
		sb.append(bucketName);
		sb.append("/");
		sb.append(request.getKey());
		return sb.toString();
	}
}
