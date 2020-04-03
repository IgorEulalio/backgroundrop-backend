package com.epass.backgroundrop.gateway;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;
import java.util.Optional;

public interface ObjectStorageGateway {

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

    public List<Bucket> getBuckets();
    public Optional<Bucket> getBucket(String bucketName);

 }
