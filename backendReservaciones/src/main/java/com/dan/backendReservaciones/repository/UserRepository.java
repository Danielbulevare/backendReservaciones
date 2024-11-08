package com.dan.backendReservaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dan.backendReservaciones.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
