package com.example.taskmaster.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InMemoryUserPoolConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder encoder)
		throws Exception {
		auth.inMemoryAuthentication().withUser("user")
			.password(encoder.encode("password")).roles("USER").and().withUser("user2")
			.password(encoder.encode("password")).roles("USER").and().withUser("user3")
			.password(encoder.encode("password")).roles("USERR");
	}

}
