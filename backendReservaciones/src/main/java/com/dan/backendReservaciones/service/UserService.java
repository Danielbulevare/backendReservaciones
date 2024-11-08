package com.dan.backendReservaciones.service;

import java.util.Optional;

import com.dan.backendReservaciones.entity.User;

public interface UserService {
	User registerUser(User user);
	Optional<User> findByUserId(Long id);
}
