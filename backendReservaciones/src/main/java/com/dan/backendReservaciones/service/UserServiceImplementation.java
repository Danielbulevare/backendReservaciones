package com.dan.backendReservaciones.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByUserId(Long id) {
		return userRepository.findByUserId(id);
	}
}
