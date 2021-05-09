package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.config.AuthRequest;
import com.api.entities.User;
import com.api.jwt.TokenGenerator;
import com.api.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	

	@PostMapping(value = {"","/"})
    public ResponseEntity<User> signIn(@RequestBody AuthRequest authRequest) {

		 final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
	        String token = tokenGenerator.generateToken(userDetails);
	        
			User user = userService.findUser(authRequest.getUsername());
			
//	        if (userService.findUser(authRequest.getUsername()) != null) {
//				user.setToken(token);
//				userService.save(user);
//			}
	        
	        return ResponseEntity.ok()
	                .header(
	                    HttpHeaders.AUTHORIZATION,
	                    token
	                ).body(user);
		
        
    }
	
}
