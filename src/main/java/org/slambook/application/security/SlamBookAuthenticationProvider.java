package org.slambook.application.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slambook.application.domain.SlamBookUser;
import org.slambook.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mongodb.MongoClient;

@Service
public class SlamBookAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	@Autowired
	MongoClient mongoClient;

	@Value("${dbname}")
	private String dbName;

	@Value("${collection}")
	private String collection;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			throw new BadCredentialsException("Please enter password");
		}
		SlamBookUser user = userService.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Login");
		}
		/*if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Invalid Login");
		}*/
		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Invalid Login");
		}
		final List<GrantedAuthority> auths;
        if (!user.getRole().isEmpty()) {
	    	auths = AuthorityUtils.createAuthorityList(user.getRole().toUpperCase());
        } else {
        	auths = AuthorityUtils.NO_AUTHORITIES;
        }
        return new User(username, password, true, true, true, true, auths);
	}

}
