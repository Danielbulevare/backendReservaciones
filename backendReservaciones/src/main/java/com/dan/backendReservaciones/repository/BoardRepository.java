package com.dan.backendReservaciones.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.projection.interfacebased.closed.BoardInterfaceClosedView;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	long countByBlockedByUserAndIsReserved(String blockedByUser, boolean IsReserved);
	Page<BoardInterfaceClosedView> findByBlockedByUserAndIsReserved(String blockedByUser, boolean IsReserved, Pageable pageable);
}
