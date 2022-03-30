package com.api.tools.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.tools.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
List<UserModel> findAllByUserNameContainingIgnoreCase (String userName);
Optional<UserModel> findByUserName (String userName);
}
