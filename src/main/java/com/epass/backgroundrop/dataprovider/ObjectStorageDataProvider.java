package com.epass.backgroundrop.dataprovider;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.epass.backgroundrop.gateway.ObjectStorageGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public List<String> getBucketObjects(String bucketName){
        List<String> keys = new ArrayList<>();
        var objectsS3 = getObjectsAmazonS3(bucketName);
        objectsS3.forEach(object -> keys.add(object.getKey()));
        return keys;
    }

    public Object getBucketObject(String bucketName, String keyName) throws IOException {
        S3ObjectInputStream objectContent = getS3Client().getObject(new GetObjectRequest(bucketName, keyName)).getObjectContent();
        displayTextInputStream(objectContent);
        return objectContent;
    };

    private List<S3ObjectSummary> getObjectsAmazonS3(String bucketName) {
        ListObjectsRequest listRequest = new ListObjectsRequest().withBucketName(bucketName);
        return getS3Client().listObjects(listRequest).getObjectSummaries();
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }

}
