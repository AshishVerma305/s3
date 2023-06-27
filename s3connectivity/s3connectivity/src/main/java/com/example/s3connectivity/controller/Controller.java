package com.example.s3connectivity.controller;


import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@Slf4j
public class Controller {
    @GetMapping("/connect")
    public void connect() {

        // Create an S3Client with the private endpoint configuratio
        Region region = Region.US_EAST_2;
        String endpoint = "s3-us-east-2.amazonaws.com";

        // Create an S3Client with the private endpoint configuration
        S3Client s3Client = S3Client.builder()
                .region(region)
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        // List the S3 buckets
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3Client.listBuckets(listBucketsRequest);

        // Print the bucket names
        System.out.println("S3 Buckets:");
        listBucketsResponse.buckets().forEach(bucket -> System.out.println(bucket.name()));

        // Close the S3Client
        s3Client.close();
    }
}
