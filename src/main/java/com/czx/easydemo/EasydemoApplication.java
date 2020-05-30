package com.czx.easydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan({"com.czx.easydemo.mapper"})
public class EasydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasydemoApplication.class, args);
	}

	@Bean
	public Redisson redisson(){
		//此为单机模式
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
		return (Redisson) Redisson.create(config);
	}

}

