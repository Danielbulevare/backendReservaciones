package com.dan.backendReservaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dan.backendReservaciones.service.BoardService;

@RestController
@RequestMapping("api/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@PutMapping("/lockBoard/{boardId}/{userEmail}")
	public boolean lockBoard(@PathVariable Long boardId, @PathVariable String userEmail) {
		return boardService.lockBoard(boardId, userEmail);
	}
	
	@PutMapping("/unlockingBoard/{boardId}")
	public boolean unlockingBoard(@PathVariable Long boardId) {
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
}
