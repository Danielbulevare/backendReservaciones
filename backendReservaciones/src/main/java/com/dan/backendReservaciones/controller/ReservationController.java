package com.dan.backendReservaciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.error.RecordNotFoundException;
import com.dan.backendReservaciones.projection.classbased.ReservationDataDTO;
import com.dan.backendReservaciones.projection.interfacebased.closed.ReservationInterfaceClosedView;
import com.dan.backendReservaciones.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
	@Autowired
	ReservationService reservationService;

	@PostMapping("/reserve")
	public Optional<ReservationDataDTO> reserve(@Valid @RequestBody Reservation reservation)
			throws RecordNotFoundException {
		return reservationService.reserve(reservation);
	}

	@GetMapping("/reservationHistory")
	public List<ReservationInterfaceClosedView> reservationHistory(@RequestBody User user)
			throws RecordNotFoundException {
		return reservationService.reservationHistory(user);
	}

	@PutMapping("/cancelReservation/{reservationId}")
	public boolean cancelReservation(@PathVariable Long reservationId) throws RecordNotFoundException {
		return reservationService.cancelReservation(reservationId);
	}
}
