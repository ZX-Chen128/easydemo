package com.czx.easydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan({"com.czx.easydemo.mapper"})
public class EasydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasydemoApplication.class, args);
	}

}
