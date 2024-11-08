package com.dan.backendReservaciones.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_tables")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long boardId;
	
	@NotBlank(message = "Debes asignar un número (nombre) a la mesa.")
	@Length(max = 3, message = "El nombre supera los 3 caracteres.")
	@Column(name = "table_number", nullable = false, unique = true)
	private String boardNumber;
	
	@NotNull(message = "Debes especificar el estatus de la mesa.")
	@Column(name = "reserved", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isReserved;

	@Email(message = "Debes asignar un correo válido.")
	@Column(name = "blocked_by_user", nullable = true, unique = true)
	@Length(max = 50)
	private String blockedByUser;
}
