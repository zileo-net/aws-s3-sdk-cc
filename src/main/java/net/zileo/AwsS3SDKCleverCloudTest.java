package net.zileo;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class AwsS3SDKCleverCloudTest {

    public static void main(String[] args) {

        String key_id = "";
        String key_secret = "";
        String bucket = "";

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(key_id, key_secret));
        S3Client s3client = S3Client.builder()
                .endpointOverride(URI.create("https://cellar-c2.services.clever-cloud.com"))
                .credentialsProvider(credentialsProvider)
                .region(Region.EU_CENTRAL_1)
                .forcePathStyle(Boolean.TRUE)
                .build();

        try {
            System.out.println("Listing files from CC S3...");
            var request = ListObjectsV2Request.builder().bucket(bucket).build();
            s3client.listObjectsV2(request).contents()
                    .forEach(object -> System.out.println(" - " + object.key()));
            System.out.println("Done.");
        } catch (AwsServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Uploading file to CC S3...");
            PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key("my_file_2_30_38.txt").build();
            s3client.putObject(request, Path.of(new File("src/main/resources/file.txt").toURI()));
            System.out.println("Done.");
        } catch (AwsServiceException e) {
           System.out.println("Error : " +  e.awsErrorDetails().errorCode() + " " + e.awsErrorDetails().errorMessage());
           e.printStackTrace();
        }

    }

}
