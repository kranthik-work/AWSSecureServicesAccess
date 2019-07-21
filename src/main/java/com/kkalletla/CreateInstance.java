package com.kkalletla;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;

import java.util.Scanner;

/**
 * Creates an EC2 instance
 */
public class CreateInstance
{
    public static void main(String[] args)
    {
        final String USAGE =
                "To run this example, supply an instance name and AMI image id\n" +
                        "Ex: CreateInstance <instance-name> <ami-image-id>\n";

        /*if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }*/

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        String ami_id = scanner.nextLine();

        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

        RunInstancesRequest run_request = new RunInstancesRequest()
                .withImageId(ami_id)
                .withInstanceType(InstanceType.T2Micro)
                .withMaxCount(1)
                .withMinCount(1);

        RunInstancesResult run_response = ec2.runInstances(run_request);

        String instance_id = run_response.getReservation().getReservationId();

        Tag tag = new Tag()
                .withKey("Name")
                .withValue(name);

        CreateTagsRequest tag_request = new CreateTagsRequest()
                .withTags(tag);

        CreateTagsResult tag_response = ec2.createTags(tag_request);

        System.out.printf(
                "Successfully started EC2 instance %s based on AMI %s",
                instance_id, ami_id);
    }
}