package com.dan.backendReservaciones.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.entity.Reservation;
import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.enumeration.Status;
import com.dan.backendReservaciones.projection.interfacebased.closed.ReservationInterfaceClosedView;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Long countByReservationDateAndStatusAndBoard(LocalDate reservationDate, Status status, Board board);
	Long countByStatusAndUser(Status status, User user);
	List<ReservationInterfaceClosedView> findByUser(User user);
}
