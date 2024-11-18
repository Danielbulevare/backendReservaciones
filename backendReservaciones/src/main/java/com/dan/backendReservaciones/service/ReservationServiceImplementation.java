package com.dan.backendReservaciones.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.enumeration.Status;
import com.dan.backendReservaciones.error.RecordNotFoundException;
import com.dan.backendReservaciones.projection.classbased.ReservationDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.ReservationInterfaceClosedView;
import com.dan.backendReservaciones.repository.BoardRepository;
import com.dan.backendReservaciones.repository.ReservationRepository;
import com.dan.backendReservaciones.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImplementation implements ReservationService {
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public Optional<ReservationDataDTO> reserve(Reservation reservation) throws RecordNotFoundException {
		Optional<User> userDB = userRepository.findById(reservation.getUser().getUserId());

		// Si el usuario no existe
		if (!userDB.isPresent()) {
			throw new RecordNotFoundException("El usuario que está intentado reservar no existe.");
		}

		Optional<Board> boardToReserve = boardRepository.findById(reservation.getBoard().getBoardId());

		// Si la mesa no existe
		if (!boardToReserve.isPresent()) {
			throw new RecordNotFoundException("La mesa que se está intentando reservar no existe.");
		}

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

		// Si el usuario que está reservando no es el usuario que apartó o bloqueó la
		// mesa
		if (!reservation.getUser().getUserEmail().equals(boardToReserve.get().getBlockedByUser()))
			return Optional.empty();

		// Guarda la reservación insertada
		Optional<Reservation> reservationCreated = Optional.ofNullable(reservationRepository.save(reservation));

		Optional<ReservationDataDTO> reservationDataDTO = Optional
				.ofNullable(new ReservationDataDTO(reservationCreated.get().getReservationId(),
						reservationCreated.get().getReservationDate(), reservationCreated.get().getStatus(),
						reservationCreated.get().getUser(), reservationCreated.get().getBoard()));

		LocalDate dateTomorrow = currentDate.plusDays(1);

		// Si la fecha de reservación es mañana
		if (reservation.getReservationDate().isEqual(dateTomorrow))
			boardToReserve.get().setIsReserved(true); // Marcar la mesa como reservada

		boardToReserve.get().setBlockedByUser(null); // Desbloquea la mesa
		boardRepository.save(boardToReserve.get()); // Actualiza la mesa

		return reservationDataDTO;
	}

	@Override
	public List<ReservationInterfaceClosedView> reservationHistory(User user) throws RecordNotFoundException {
		Optional<User> userDB = userRepository.findById(user.getUserId());

		// Si no existe el usuario
		if (!userDB.isPresent()) {
			throw new RecordNotFoundException("El usuario no existe.");
		}

		return reservationRepository.findByUser(user);
	}

	@Override
	@Transactional
	public boolean cancelReservation(Long reservationId) throws RecordNotFoundException {
		Optional<Reservation> reservationDB = reservationRepository.findById(reservationId);

		// Si no se encontró la reservación
		if (!reservationDB.isPresent())
			throw new RecordNotFoundException("La reservación especificada no existe.");

		// Si la reservación está cancelada
		if (reservationDB.get().getStatus().equals(Status.CANCELADO))
			return false;

		LocalDate currentDate = LocalDate.now();

		// Si la fecha de reservación es menor o igual a la fecha actual
		if (reservationDB.get().getReservationDate().isBefore(currentDate)
				|| reservationDB.get().getReservationDate().isEqual(currentDate))
			return false;

		reservationDB.get().setStatus(Status.CANCELADO); // Cancela la reservación

		LocalDate dateTomorrow = currentDate.plusDays(1);

		// Si la fecha de reservación es mañana
		if (reservationDB.get().getReservationDate().isEqual(dateTomorrow)) {
			Board boardDB = boardRepository.findById(reservationDB.get().getBoard().getBoardId()).get();
			boardDB.setIsReserved(false); // Cancela la reservación
		}

		return true;
	}

}
