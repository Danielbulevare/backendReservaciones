package com.dan.backendReservaciones.projection.classbased;

import com.dan.backendReservaciones.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDataDTO {
	private Long userId;
	private String userName;
	private String userEmail;
	private Role userRole;
}
