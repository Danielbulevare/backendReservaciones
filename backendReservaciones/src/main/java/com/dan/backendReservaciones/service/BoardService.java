package com.dan.backendReservaciones.service;

import java.util.List;

import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.error.RecordNotFoundException;
import com.dan.backendReservaciones.projection.interfacebased.closed.BoardInterfaceClosedView;

public interface BoardService {
	boolean lockBoard(Long boardId, String blockedByUser) throws EmailAlreadyExistsException, RecordNotFoundException;
	boolean unlockingBoard(Long boardId) throws RecordNotFoundException;
	long countBoard();
	long countBoardAvailability();
	long totalPages(int records);
	List<BoardInterfaceClosedView> boardsAvailable(int page, int records);
}
