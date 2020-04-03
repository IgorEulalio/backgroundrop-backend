package com.epass.backgroundrop.dataprovider;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.epass.backgroundrop.gateway.ObjectStorageGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ObjectStorageDataProvider implements ObjectStorageGateway {

    @Value("${bucket-object-storage.name}")
    public String bucketName;

    @Value("${bucket-object-storage.key}")
    public String key;

    private static AmazonS3 getS3Client() {
        try {

            AmazonS3 s3client = AmazonS3ClientBuilder
                    .standard()
                    .withRegion(Regions.SA_EAST_1)
                    .build();

            return s3client;

        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> getBuckets() {
        List<Bucket> buckets = getS3Client().listBuckets();
        return buckets.stream().map(bucket -> bucket.getName()).collect(Collectors.toList());
    }

    public Optional<Bucket> getBucket(String bucketName) {
        return getS3Client().listBuckets().stream().filter(b -> b.getName().equals(bucketName)).findFirst();
    }

    @Override
    public Object getObject() {
        var fullObject = getS3Client().getObject(new GetObjectRequest(bucketName, key));
        return fullObject;
    }

}
