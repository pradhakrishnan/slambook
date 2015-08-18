package org.slambook.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Configuration
@EnableWebMvcSecurity
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SlamBookSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SlamBookAuthenticationProvider aProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests().antMatchers(HttpMethod.GET, "/register").permitAll()
		.and().authorizeRequests().antMatchers("/header.html").permitAll()
		.and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/admin.html").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/**").hasAnyRole("USER")
		.and().authorizeRequests().antMatchers("/search").hasAnyRole("USER")
         .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/dashboard").and().logout().permitAll();*/
		http.authorizeRequests().antMatchers("/register").permitAll()
		.and().authorizeRequests().antMatchers("/dashboard").permitAll()
		.and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/admin.html").hasAnyRole("ADMIN")
		.and().authorizeRequests().antMatchers("/search").hasAnyRole("USER")
        .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/dashboard").and().logout().permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(aProvider);
	}
}
