package com.project.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class configuration {

//	@Bean
//	public UserDetailsService userDetailService() {
//
//		// TO set username and password
//		UserDetails user1 = User.builder().username("user").password(passwordEncoder().encode("user")).build();//roles("ADMIN")
//		//		.build();
//		UserDetails user2 = User.builder().username("arushi").password(passwordEncoder().encode("arushi")).build();
//				//.roles("CUSTOMER").build();
//
//		return new InMemoryUserDetailsManager(user1, user2);
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
}
