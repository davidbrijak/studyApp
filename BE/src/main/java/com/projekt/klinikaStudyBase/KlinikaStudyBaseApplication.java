package com.projekt.klinikaStudyBase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.projekt.klinikaStudyBase.data.entity")
@EnableTransactionManagement
public class KlinikaStudyBaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(KlinikaStudyBaseApplication.class, args);
	}
}
