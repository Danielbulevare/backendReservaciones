package com.dan.backendReservaciones.service;

import java.util.List;
import java.util.Optional;

import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;

public interface ReservationService {
	Optional<Reservation> reserve(Reservation reservation);
	List<Reservation> reservationHistory(User user);
	boolean cancelReservation(Long reservationId);
}
