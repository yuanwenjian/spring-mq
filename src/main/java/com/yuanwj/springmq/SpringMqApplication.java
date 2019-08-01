package com.yuanwj.springmq;

import com.yuanwj.springmq.config.MqConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableConfigurationProperties(MqConfigProperties.class)
@EnableDiscoveryClient
public class SpringMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMqApplication.class, args);
	}
}
