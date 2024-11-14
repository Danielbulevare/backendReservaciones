package com.dan.backendReservaciones.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.enumeration.Status;
import com.dan.backendReservaciones.repository.BoardRepository;
import com.dan.backendReservaciones.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImplementation implements ReservationService {
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	BoardRepository boardRepository;

	@Override
	@Transactional
	public Optional<Reservation> reserve(Reservation reservation) {
		LocalDate currentDate = LocalDate.now();

		// Si la fecha de reservación es menor o igual a la fecha actual
		if (reservation.getReservationDate().isBefore(currentDate)
				|| reservation.getReservationDate().isEqual(currentDate))
			return Optional.empty();

		Long areThereReservations = reservationRepository.countByReservationDateAndStatusAndBoard(
				reservation.getReservationDate(), Status.RESERVADO, reservation.getBoard());

		// Si existen reservaciones en la fecha seleccionada
		if (areThereReservations > 0)
			return Optional.empty();

		// Si el usuario tiene el rol General
		if (reservation.getUser().getUserRole().getRole().equals("General")) {
			Long doYouHaveReservations = reservationRepository.countByStatusAndUser(Status.RESERVADO,
					reservation.getUser());

			// Si el usuario tiene al menos una reservación
			if (doYouHaveReservations > 0)
				return Optional.empty();
		}

		Board boardToReserve = boardRepository.findById(reservation.getBoard().getBoardId()).get();

		// Si el usuario que está reservando no es el usuario que apartó o bloqueó la
		// mesa
		if (reservation.getUser().getUserEmail() != boardToReserve.getBlockedByUser())
			return Optional.empty();

		// Guarda la reservación insertada
		Optional<Reservation> reservationCreated = Optional.ofNullable(reservationRepository.save(reservation));

		LocalDate dateTomorrow = currentDate.plusDays(1);

		// Si la fecha de reservación es mañana
		if (reservation.getReservationDate().isEqual(dateTomorrow))
			boardToReserve.setIsReserved(true); // Marcar la mesa como reservada

		boardToReserve.setBlockedByUser(null); // Desbloquea la mesa
		boardRepository.save(boardToReserve); // Actualiza la mesa

		return reservationCreated;
	}

	@Override
	public List<Reservation> reservationHistory(User user) {
		return reservationRepository.findByUser(user);
	}

	@Override
	@Transactional
	public boolean cancelReservation(Long reservationId) {
		Optional<Reservation> reservationDB = reservationRepository.findById(reservationId);

		// Si no se encontró la reservación
		if (reservationDB.isEmpty())
			return false;

		// Si la reservación está cancelada
		if (reservationDB.get().getStatus().equals(Status.CANCELADO))
			return false;

		LocalDate currentDate = LocalDate.now();

		// Si la fecha de reservación es menor o igual a la fecha actual
		if (reservationDB.get().getReservationDate().isBefore(currentDate)
				|| reservationDB.get().getReservationDate().isEqual(currentDate))
			return false;

		reservationDB.get().setStatus(Status.CANCELADO); //Cancela la reservación
		
		LocalDate dateTomorrow = currentDate.plusDays(1);

		// Si la fecha de reservación es mañana
		if (reservationDB.get().getReservationDate().isEqual(dateTomorrow)) {
			Board boardDB = boardRepository.findById(reservationDB.get().getBoard().getBoardId()).get();
			boardDB.setIsReserved(false); //Cancela la reservación
		}			

		return true;
	}

}
