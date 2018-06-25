package com.yuanwj.springmq;

import com.yuanwj.springmq.config.MqConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MqConfigProperties.class)
public class SpringMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMqApplication.class, args);
	}
}
