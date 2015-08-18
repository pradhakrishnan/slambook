package org.slambook.application;

import java.net.UnknownHostException;

import org.slambook.application.service.websocket.SlamBookWebSocketHandler;
import org.slambook.mongoservices.SlamBookServices;
import org.slambook.mongoservices.SlamBookUserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;

@SpringBootApplication
@ComponentScan
@Configuration
public class SlambookApplication extends SpringBootServletInitializer implements WebSocketConfigurer {
	
	@Value("${dbname}")
	private String dbName;

	@Value("${mongoHost}")
	private String mongoHost;
	
	@Value("${mongoPort}")
	private int mongoPort;
	
	@Value("${imageCollection}")
	private String imgColection;
	
	@Autowired
	private SlamBookWebSocketHandler webSocketHandler;


	@Bean(name="encoder")
	public PasswordEncoder getEncoder(){
		return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
	}
	
	@Bean(name="slamBookUserService")
	public SlamBookServices<org.slambook.mongoservices.domain.SlamBookUser> getSlamBookService(){
		return new SlamBookUserServicesImpl(mongoHost, mongoPort, dbName);
	}
	
	@Bean(name="gfsPhoto")
	public GridFS getGridFS() throws UnknownHostException{
		return new GridFS(new MongoClient(mongoHost, mongoPort).getDB(dbName),imgColection);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SlambookApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/admin/ws").setAllowedOrigins("*");
	}
}
