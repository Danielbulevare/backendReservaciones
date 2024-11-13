package com.dan.backendReservaciones.service;

public interface BoardService {
	boolean lockBoard(Long boardId, String blockedByUser);
	boolean unlockingBoard(Long boardId);
	long countBoard();
	long countBoardAvailability();
}
