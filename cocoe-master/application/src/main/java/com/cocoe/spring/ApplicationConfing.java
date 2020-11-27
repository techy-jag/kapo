package com.cocoe.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = "com.cocoe.spring")
public class ApplicationConfing {
	 private static final Logger logger = LoggerFactory.getLogger(ApplicationConfing.class); 
	 	     public static void main(String[] args) {	    	 
	         SpringApplication.run(ApplicationConfing.class, args);	 
	         logger.info("Cocoe Application Started........");
	 
	     }
	     
	   

}
