package com.integrador.back.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integrador.back.model.olxModel;

@Repository
public interface olxRepository extends JpaRepository<olxModel,UUID>{

}
