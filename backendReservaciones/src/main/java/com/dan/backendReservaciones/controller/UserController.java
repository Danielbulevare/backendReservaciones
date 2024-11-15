package com.dan.backendReservaciones.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.projection.classbased.UserDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.UserInterfaceClosedView;
import com.dan.backendReservaciones.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/registerUser")
	public UserDataDTO registerUser(@Valid @RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping("/findByUserId/{id}")
	public Optional<UserInterfaceClosedView> findByUserId(@PathVariable Long id){
		return userService.findByUserId(id);
	}

}
