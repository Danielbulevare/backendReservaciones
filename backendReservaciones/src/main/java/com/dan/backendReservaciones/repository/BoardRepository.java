package com.dan.backendReservaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.backendReservaciones.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	long countByBlockedByUserAndIsReserved(String blockedByUser, boolean IsReserved);
}
