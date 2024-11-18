package com.dan.backendReservaciones.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dan.backendReservaciones.entity.Board;
import com.dan.backendReservaciones.error.EmailAlreadyExistsException;
import com.dan.backendReservaciones.error.RecordNotFoundException;
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
	public boolean lockBoard(Long boardId, String blockedByUser)
			throws EmailAlreadyExistsException, RecordNotFoundException {
		Optional<Board> boardDB = boardRepository.findById(boardId);

		if (!boardDB.isPresent()) {
			throw new RecordNotFoundException("La mesa especificada no existe.");
		}

		if (Objects.nonNull(blockedByUser)) {
			boardDB.get().setBlockedByUser(blockedByUser);
		}

		try {

			// Guardar la entidad y comprueba si se guarda correctamente
			// Si no regresa nulo, significa que la operación fue exitosa
			return boardRepository.save(boardDB.get()) != null;
		} catch (DataIntegrityViolationException e) {
			throw new EmailAlreadyExistsException("El correo electrónico ya existe.");
		}
	}

	@Override
	public boolean unlockingBoard(Long boardId) throws RecordNotFoundException {
		Optional<Board> boardDB = boardRepository.findById(boardId);

		if (!boardDB.isPresent()) {
			throw new RecordNotFoundException("La mesa especificada no existe.");
		}

		boardDB.get().setBlockedByUser(null);

		return boardRepository.save(boardDB.get()) != null;
	}

}
