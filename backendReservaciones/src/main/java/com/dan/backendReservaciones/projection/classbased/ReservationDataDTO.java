package com.dan.backendReservaciones.projection.classbased;

import java.time.LocalDate;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDataDTO {
	private Long reservationId;
	private LocalDate reservationDate;
	private Status status;
	private User user;
	private Board board;
}
