package com.kkalletla;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class CreateBucket
{

    private String accessKeyId;
    private String secretKeyId;


    public CreateBucket(String accessKeyId, String secretKeyId) {
        this.accessKeyId = accessKeyId;
        this.secretKeyId = secretKeyId;

        System.out.println("In Constructor");
        System.out.println(accessKeyId);
        System.out.println(secretKeyId);
    }

    public static Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        System.out.println("Printing List of buckets");
        outer: for (Bucket b : buckets) {
            System.out.println(b.getName());
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
                break outer;
            }
        }
        return named_bucket;
    }

    public static void createBucket() {

        String bucket_name;

        System.out.print("\n\nName of the bucket: ");
        Scanner sc = new Scanner(System.in);
        bucket_name =sc.nextLine();

        System.out.format("\nCreating S3 bucket: %s\n", bucket_name);

        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
            System.out.format("Bucket %s already exists.\n", bucket_name);
            b = getBucket(bucket_name);
        } else {
            try {
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        if (b == null) {
            System.out.println("Error creating bucket!\n");
        } else {
            System.out.println("Done!\n");
        }
    }
}