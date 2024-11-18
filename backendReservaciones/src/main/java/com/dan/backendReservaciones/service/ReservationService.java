package com.dan.backendReservaciones.service;

import java.util.List;
import java.util.Optional;

import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.error.RecordNotFoundException;
import com.dan.backendReservaciones.projection.classbased.ReservationDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.ReservationInterfaceClosedView;

public interface ReservationService {
	Optional<ReservationDataDTO> reserve(Reservation reservation) throws RecordNotFoundException;

	List<ReservationInterfaceClosedView> reservationHistory(User user) throws RecordNotFoundException;

	boolean cancelReservation(Long reservationId) throws RecordNotFoundException;
}
