package com.dan.backendReservaciones.projection.interfacebased.closed;

import java.time.LocalDate;

import com.dan.backendReservaciones.enumeration.Status;

public interface ReservationInterfaceClosedView {
	Long getReservationId();
	LocalDate getReservationDate();
	Status getStatus();
	UserInterfaceClosedView getUser();
	BoardInterfaceClosedView getBoard();
}
