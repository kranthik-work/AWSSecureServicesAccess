package com.kkalletla.AWSSecureServicesAccess;

import com.kkalletla.CreateBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsSecureServicesAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSecureServicesAccessApplication.class, args);

		CreateBucket.createBucket();
	}
}
