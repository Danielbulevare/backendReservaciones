package com.dan.backendReservaciones.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.projection.classbased.UserDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.UserInterfaceClosedView;
import com.dan.backendReservaciones.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDataDTO registerUser(User user) {
		User userDB = userRepository.save(user);
		UserDataDTO userDataDTO = new UserDataDTO(userDB.getUserId()
				, userDB.getUserName()
				, userDB.getUserEmail()
				, userDB.getUserRole());
		return userDataDTO;
	}

	@Override
	public Optional<UserInterfaceClosedView> findByUserId(Long id) {
		return userRepository.findByUserId(id);
	}
}
