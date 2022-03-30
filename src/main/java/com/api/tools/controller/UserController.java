package com.api.tools.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.tools.model.UserModel;
import com.api.tools.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserModel>> getAlluser () {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
	}
	

	@PostMapping("/register")
	public ResponseEntity<UserModel> Post(@Valid @RequestBody UserModel userDto) {
		return userService.registerUser(userDto)
				.map(registeredUser -> ResponseEntity.status(201).body(registeredUser))
				.orElse(ResponseEntity.badRequest().build());

	}
	
}
