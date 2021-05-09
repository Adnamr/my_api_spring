package com.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api.entities.User;
import com.api.services.UserService;

@Component
public class FirstTimeInit implements CommandLineRunner {

	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if (userService.findAll().isEmpty()) 
		{
			log.info("FirstTimeInit .....");

			User user = new User("1", "adnane.amraoui@outlook.fr", "Adnane", "123456", "");
			
			userService.save(user);
		}
		
	}

}
