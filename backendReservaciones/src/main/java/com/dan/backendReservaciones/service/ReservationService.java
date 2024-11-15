package com.dan.backendReservaciones.service;

import java.util.List;
import java.util.Optional;

import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.projection.classbased.ReservationDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.ReservationInterfaceClosedView;

public interface ReservationService {
	Optional<ReservationDataDTO> reserve(Reservation reservation);
	List<ReservationInterfaceClosedView> reservationHistory(User user);
	boolean cancelReservation(Long reservationId);
}
