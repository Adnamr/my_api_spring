package com.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.jwt.TokenGenerator;
import com.api.services.UserService;

public class AuthFilter extends OncePerRequestFilter {

	
	@Value("${auth.header}")
    private String TOKEN_HEADER;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	 
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String header = req.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();
		
        
        if (header != null && securityContext.getAuthentication() == null) 
        {
            String token = header.substring("Bearer ".length());
            String username = tokenGenerator.getUserNameFromToken(token);
            if(username != null) 
            {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (tokenGenerator.isTokenValid(token, userDetails)) 
                {
                	
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
		
		doFilter(req, res, filterChain);
	}

}
