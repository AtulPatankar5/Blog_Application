package com.project.blog.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private JWTHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {// it will run before request and check if token in contained in the
													// header of request

		// Authorization=Bearer 234231432432
		// 01234567___________
		String requestHeader = request.getHeader("Authorization");// key to be passed in POSTMan IN header
		logger.info(" Header :  {}", requestHeader);
		String username = null;
		String token = null;

		//if token is present then fetch the token without Bearer starting
 		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			token = requestHeader.substring(7);
			try {
				username = jwtHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				logger.info("Illegal Argument while fetching the username");
//				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				logger.info("Given JWT Token is expired");
//				e.printStackTrace();
			} catch (MalformedJwtException e) {
				logger.info("SOme changes has been made in the token  !! Invalid token ");
//				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			logger.info("JWT TOken doesnot begin with Bearer!!");
		}

		
		//if token is present , it will find the username present in TOken  and validate it 
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// fetch user details from username
			UserDetails userDetails = userDetailService.loadUserByUsername(username);
			Boolean validateToken = jwtHelper.validateToken(token, userDetails);
			if (validateToken) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);// store authentication details in
																						// the secuirtycontextHolder
			} else {
				logger.info("Validation Fails ");
			}

		} else {
			logger.info("Username is null or context is not null");
		}
		filterChain.doFilter(request, response);
	}

}
