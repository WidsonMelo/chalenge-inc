package com.widson.chalengeinc.services;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){	
		List<User> uses = userRepository.findAll(); 
		return uses;
	}	
	
	public User findById(Integer id){
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	public User save(User user) {
		userRepository.save(user);
		return user;
	}
	
	public void delete(User user) {
		Optional<User> userOptional = userRepository.findById(user.getId());
		if (userOptional.isPresent()) {
			userRepository.delete(user);
		}
	}

}
