package org.slambook.application.service;

import org.slambook.application.domain.SlamBookUser;
import org.slambook.application.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserAccountRepository accountRepository;
	
	public SlamBookUser getByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	
	public SlamBookUser insert(SlamBookUser user){
		return accountRepository.insert(user);
	}
}
