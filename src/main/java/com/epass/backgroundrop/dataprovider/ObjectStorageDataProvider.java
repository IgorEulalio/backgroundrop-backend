package com.epass.backgroundrop.dataprovider;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.epass.backgroundrop.gateway.ObjectStorageGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public List<Bucket> getBuckets(){
        System.out.println(this.key + this.bucketName);
        return getS3Client().listBuckets();
    }

    public Optional<Bucket> getBucket(String bucketName){
        return getS3Client().listBuckets().stream().filter(bucket -> bucket.getName().equals(bucketName)).findFirst();
    }

}
