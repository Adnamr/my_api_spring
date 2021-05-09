package com.api.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.entities.User;
import com.api.exception.NotExistException;
import com.api.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).orElseThrow(() -> new NotExistException(username + " Not found"));
		
		log.info("user :: "+user.getEmail());
		
		return user;
	}
	
	public User save(User user)
	{
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	
	public List<User> findAll()
	{
		List<User> users = new ArrayList<>();
		
		userRepository.findAll().forEach(users::add);
		
		return users;
	}
	
	public User findUser(String username)
	{
		User user = userRepository.findByEmail(username).orElseThrow(() -> new NotExistException(username + " Not found"));
		
		if (user == null) 
		{
			throw new NotExistException("User Not found");
		}
		
		log.info("user :: "+user.getEmail());
		
		return user;
	}
	

}
