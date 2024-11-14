package com.dan.backendReservaciones.entity;

import java.time.LocalDate;

import com.dan.backendReservaciones.enumeration.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long reservationId;

	@Column(name = "reservation_date", nullable = false)
	private LocalDate reservationDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, columnDefinition = "ENUM('RESERVADO', 'CANCELADO') DEFAULT 'RESERVADO'")
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_user", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_board", referencedColumnName = "board_id", nullable = false)
	private Board board;
}
