package com.dan.backendReservaciones.service;

import java.util.Optional;

import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.projection.classbased.UserDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.UserInterfaceClosedView;

public interface UserService {
	UserDataDTO registerUser(User user);
	Optional<UserInterfaceClosedView> findByUserId(Long id);
}
