package com.example.vben_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.vben_server.mapper") // 扫描 Mapper 接口
@EnableTransactionManagement // 开启事务注解
public class VbenServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VbenServerApplication.class, args);
	}

}
