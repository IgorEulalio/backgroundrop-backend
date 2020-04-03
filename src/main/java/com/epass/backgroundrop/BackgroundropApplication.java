package com.epass.backgroundrop;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;

public class BackgroundropApplication {
    public static void main(String[] args) throws IOException {

        String bucketName = "itau-study";
        String key = "test.json";

        /*var awsCreds = new BasicAWSCredentials("AKIA3KSG7BUX36LTLX6C", "flsjeA88TQdOPZ/F4yi8g0rHPdX5YyBO2uLqGJl2");*/

        try {

            AmazonS3 s3client = AmazonS3ClientBuilder
                    .standard()
                    /*.withCredentials(new AWSStaticCredentialsProvider(awsCreds))*/
                    .withRegion(Regions.SA_EAST_1)
                    .build();

            System.out.println(s3client.listBuckets());
        }
        catch (Exception e){
            throw e;
        }
    }
}


