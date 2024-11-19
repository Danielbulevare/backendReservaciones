package com.dan.backendReservaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.config.JwtService;
import com.dan.backendReservaciones.controller.models.AuthResponse;
import com.dan.backendReservaciones.controller.models.AuthenticationRequest;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.projection.classbased.UserDataDTO;
import com.dan.backendReservaciones.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepository;
	
	private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

	@Override
	public UserDataDTO registerUser(User user) throws EmailAlreadyExistsException {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			User userDB = userRepository.save(user);
			UserDataDTO userDataDTO = new UserDataDTO(userDB.getUserId(), userDB.getUsername(), userDB.getUserEmail(),
					userDB.getUserRole());
			return userDataDTO;
		} catch (DataIntegrityViolationException e) {
			throw new EmailAlreadyExistsException("El correo electrónico ya existe.");
		}
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserEmail(),request.getPassword()
                )
        );

        
        User user = userRepository.findByUserEmail(request.getUserEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwtToken).build();
	}
}
