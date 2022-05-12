package com.topsun.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.topsun.websocket.UDPAccpet;
import com.topsun.websocket.UDPReceive;

@ComponentScan("com.topsun.*")
@MapperScan(basePackages={"com.topsun.mapper"})
//@EnableAutoConfiguration
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
		new UDPReceive().start();

		new UDPAccpet().start();
		
	}
	

}
