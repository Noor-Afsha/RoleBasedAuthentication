package com.auth.RoleBasedAuthorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SpringConfig {

	// password encoder

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// to create custom user
	@Bean
	public UserDetailsService userDetailsService() {

		// interface

		UserDetails admin = User.builder().username("noor").password(passwordEncoder().encode("noor@123"))
				.roles("ADMIN").build();
		
		UserDetails user = User.builder().username("noori").password(passwordEncoder().encode("noor@1234"))
				.roles("USER").build();
	

		return new InMemoryUserDetailsManager(admin, user);

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
				authorize -> authorize
				.requestMatchers(HttpMethod.GET).hasRole("USER")
				.requestMatchers(HttpMethod.POST).hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE).hasRole("USER")


				.anyRequest().authenticated())
				
				.httpBasic(Customizer.withDefaults()).build();
	}
}