package com.dan.backendReservaciones.service;

import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.error.RecordNotFoundException;

public interface BoardService {
	boolean lockBoard(Long boardId, String blockedByUser) throws EmailAlreadyExistsException, RecordNotFoundException;
	boolean unlockingBoard(Long boardId) throws RecordNotFoundException;
	long countBoard();
	long countBoardAvailability();
}
