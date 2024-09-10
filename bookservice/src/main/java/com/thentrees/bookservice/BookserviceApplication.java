package com.thentrees.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.thentrees.bookservice", "com.thentrees.commonservice"})
public class BookserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}

}
