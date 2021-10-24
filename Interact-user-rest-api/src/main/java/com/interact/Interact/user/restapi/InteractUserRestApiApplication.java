package com.interact.Interact.user.restapi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class InteractUserRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InteractUserRestApiApplication.class, args);
	}

}
