package com.syria.xtErp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.syria.xtErp.storage.StorageProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AdlerXtErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdlerXtErpApplication.class, args);
	}
}
