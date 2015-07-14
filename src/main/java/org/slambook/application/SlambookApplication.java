package org.slambook.application;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@SpringBootApplication
@ComponentScan
@Configuration
@ImportResource("spring/mongo.xml")
@EnableMongoRepositories
public class SlambookApplication {

	@Bean(name = "mongoClient")
	public MongoClient getMongoClient() {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			throw new MongoException("Unable to connect to Mongo");
		}
		return mongoClient;
	}

	@Bean(name="encoder")
	public PasswordEncoder getEncoder(){
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SlambookApplication.class, args);
	}
}
