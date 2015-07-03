package org.slambook.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SlambookApplication {

	@RequestMapping("/slambook")
	public String hello(){
		return "Welcome to Slambook";
	}
	
    public static void main(String[] args) {
        SpringApplication.run(SlambookApplication.class, args);
    }
}
