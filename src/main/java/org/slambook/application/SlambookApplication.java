package org.slambook.application;

import org.slambook.mongoservices.SlamBookServices;
import org.slambook.mongoservices.SlamBookUserServicesImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ComponentScan
@Configuration
public class SlambookApplication {
	
	@Value("${dbname}")
	private String dbName;

	@Value("${mongoHost}")
	private String mongoHost;
	
	@Value("${mongoPort}")
	private int mongoPort;

	@Bean(name="encoder")
	public PasswordEncoder getEncoder(){
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
	
	@Bean(name="slamBookUserService")
	public SlamBookServices<org.slambook.mongoservices.domain.SlamBookUser> getSlamBookService(){
		return new SlamBookUserServicesImpl(mongoHost, mongoPort, dbName);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SlambookApplication.class, args);
	}
}
