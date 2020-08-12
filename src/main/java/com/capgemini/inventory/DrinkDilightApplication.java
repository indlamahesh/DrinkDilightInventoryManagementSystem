package com.capgemini.inventory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.inventory.entity.User;
import com.capgemini.inventory.web.AdminInterceptor;
import com.capgemini.inventory.web.LoginInterceptor;

@SpringBootApplication
public class DrinkDilightApplication {


	static Logger logger = LoggerFactory.getLogger(DrinkDilightApplication.class);
	
	public static void main(String[] args) {
		logger.debug("Bootstraping the application");
		SpringApplication.run(DrinkDilightApplication.class, args);
	}
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Bean(name="authenticatemap")
	public Map<String,User> getAuthenticateMap(){
		return new HashMap<>();

	}
	
	

}
