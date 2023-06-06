package com.fdmgroup.gatewaydottracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
// import org.springframework.context.annotation.Bean;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class GatewaydottrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewaydottrackerApplication.class, args);
	}

	// @Bean
	// public WebMvcConfigurer mvcConfigurer() {
	// return new WebMvcConfigurer() {
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
	// }
	// };
	// }

}
