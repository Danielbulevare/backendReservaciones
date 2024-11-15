package com.dan.backendReservaciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dan.backendReservaciones.entity.User;
import com.dan.backendReservaciones.projection.interfacebased.closed.UserInterfaceClosedView;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<UserInterfaceClosedView> findByUserId(Long id);
}
