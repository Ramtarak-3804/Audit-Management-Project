package com.mfpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mfpe.exception.ExcludeFromJacocoGeneratedReport;

@SpringBootApplication
public class AuditAuthorizationApplication {

	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) {
		SpringApplication.run(AuditAuthorizationApplication.class, args);
		
	}

}
