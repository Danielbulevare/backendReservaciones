package com.dan.backendReservaciones.entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long userId;

	@NotBlank(message = "Debes asignar un nombre al usuario.")
	@Length(max = 20, message = "El nombre supera los 20 caracteres.")
	@Column(name = "user_name", nullable = false)
	private String userName;

	@NotBlank(message = "Debes asignar un correo.")
	@Email(message = "Debes asignar un correo válido.")
	@Column(name = "user_email", nullable = false, unique = true)
	@Length(max = 50)
	private String userEmail;

	@NotBlank(message = "Debes asignar una contraseña.")
	@Column(name = "password", nullable = false)
	@Length(min = 8, max = 200)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_role", referencedColumnName = "id_role", nullable = false)
	private Role userRole;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(userRole.getRole()));
	}

	@Override
	public String getUsername() {
		return userEmail;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
