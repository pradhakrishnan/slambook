package org.slambook.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
@EnableWebSecurity
public class SlamBookSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SlamBookAuthenticationProvider aProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN","admin")
		.and().authorizeRequests().antMatchers("/admin.html").hasAnyRole("ADMIN","admin")
         .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/admin").and().logout().permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(aProvider);
	}
}
