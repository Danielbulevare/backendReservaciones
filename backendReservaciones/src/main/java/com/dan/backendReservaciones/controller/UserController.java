package com.dan.backendReservaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.backendReservaciones.controller.models.AuthResponse;
import com.dan.backendReservaciones.controller.models.AuthenticationRequest;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.projection.classbased.UserDataDTO;

import com.dan.backendReservaciones.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/registerUser")
	public UserDataDTO registerUser(@Valid @RequestBody User user) throws EmailAlreadyExistsException{
		return userService.registerUser(user);
	}
	
	@PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
