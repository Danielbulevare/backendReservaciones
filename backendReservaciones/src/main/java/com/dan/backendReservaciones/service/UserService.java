package com.dan.backendReservaciones.service;

import java.util.Optional;

import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.projection.classbased.UserDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.UserInterfaceClosedView;

public interface UserService {
	UserDataDTO registerUser(User user) throws EmailAlreadyExistsException;
	Optional<UserInterfaceClosedView> findByUserId(Long id);
}
