package com.dan.backendReservaciones.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long idRole;
	
	@NotBlank(message = "Debes asisgnar el nombre del rol.")
	@Length(max = 20, message = "El nombre del supera los 20 caracteres.")
	@Column(name = "role", nullable = false, unique = true)
	private String role;
}
