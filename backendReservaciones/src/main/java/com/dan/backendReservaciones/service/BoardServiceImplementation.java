package com.dan.backendReservaciones.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.repository.BoardRepository;

@Service
public class BoardServiceImplementation implements BoardService {
	@Autowired
	BoardRepository boardRepository;

	@Override
	public long countBoard() {
		return boardRepository.count();
	}

	@Override
	public long countBoardAvailability() {
		return boardRepository.countByBlockedByUserAndIsReserved(null, false);
	}

	@Override
	public boolean lockBoard(Long boardId, String blockedByUser) {
		Board boardDB = boardRepository.findById(boardId).get();

		if (Objects.nonNull(blockedByUser)) {
			boardDB.setBlockedByUser(blockedByUser);
		}

		try {
			// Guardar la entidad y comprobar si se guarda correctamente
			// Si no regresa nulo, significa que la operación fue exitosa
			return boardRepository.save(boardDB) != null;
		} catch (Exception e) {
			// En caso de cualquier excepción (como problemas con la base de datos),
			// regresamos false
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean unlockingBoard(Long boardId) {
		Board boardDB = boardRepository.findById(boardId).get();

		boardDB.setBlockedByUser(null);

		try {
			return boardRepository.save(boardDB) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
