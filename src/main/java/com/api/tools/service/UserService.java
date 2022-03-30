package com.api.tools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.tools.model.UserModel;
import com.api.tools.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Optional<UserModel> registerUser (UserModel newUser){
		Optional<UserModel> userExisting = userRepository.findByUserName(newUser.getUserName());
		if (userExisting.isPresent()) {
			return Optional.empty();
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passwordCriptografada = encoder.encode(newUser.getPassword());
		newUser.setPassword(passwordCriptografada);
		return Optional.ofNullable(userRepository.save(newUser));
		}
	}
	
	public List<UserModel> findAllUsers() {
		return userRepository.findAll();
	}
	
	




}
