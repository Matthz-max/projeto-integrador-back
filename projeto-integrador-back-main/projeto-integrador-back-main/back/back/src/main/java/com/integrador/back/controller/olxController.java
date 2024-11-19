package com.integrador.back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrador.back.model.olxModel;

import jakarta.persistence.Id;

@RestController
@RequestMapping("olx")
	
public class olxController {
	
	@Autowired
	olxModel repo;
  
	     @Id
        @GetMapping
        public ResponseEntity<?> Listar(){
	     List<olxModel> lista = repo
	     return ResponseEntity.ok(lista);
	     
        
        
}
}
