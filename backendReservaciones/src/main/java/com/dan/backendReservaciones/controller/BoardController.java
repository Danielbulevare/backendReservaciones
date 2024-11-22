package com.dan.backendReservaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.error.RecordNotFoundException;
import com.dan.backendReservaciones.projection.interfacebased.closed.BoardInterfaceClosedView;
import com.dan.backendReservaciones.service.BoardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@PutMapping("/lockBoard/{boardId}/{userEmail}")
	public boolean lockBoard(@Valid @PathVariable Long boardId, @Valid @PathVariable String userEmail) throws EmailAlreadyExistsException, RecordNotFoundException{
		return boardService.lockBoard(boardId, userEmail);
	}
	
	@PutMapping("/unlockingBoard/{boardId}")
	public boolean unlockingBoard(@PathVariable Long boardId) throws RecordNotFoundException{
		return boardService.unlockingBoard(boardId);
	}
	
	@GetMapping("/countBoard")
	public long countBoard() {
		return boardService.countBoard();
	}
	
	@GetMapping("/countBoardAvailability")
	public long countBoardAvailability() {
		return boardService.countBoardAvailability();
	}
	
	@GetMapping("/boardsAvailable/{page}/{records}")
	public List<BoardInterfaceClosedView> boardsAvailable(@PathVariable int page, @PathVariable int records){
		return boardService.boardsAvailable(page, records);
	}
	
	@GetMapping("/totalPages/{records}")
	public Long totalPages(@PathVariable int records) {
		return boardService.totalPages(records);
	}
}
