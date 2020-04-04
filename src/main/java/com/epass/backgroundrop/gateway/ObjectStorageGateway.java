package com.epass.backgroundrop.gateway;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ObjectStorageGateway {

    public List<String> getBuckets();
    public Optional<Bucket> getBucket(String bucketName);
    public List<String> getBucketObjects(String bucketName);
    public Object getBucketObject(String bucketName, String keyName) throws IOException;
 }
