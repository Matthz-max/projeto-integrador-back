package com.api.integrador.back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.integrador.back.dto.CarroDTO;
import com.api.integrador.back.model.Carromodel;
import com.api.integrador.back.repository.CarroRepository;

@RestController
@RequestMapping("carro")  
public class CarroController {

    @Autowired
    private CarroRepository repo; 

    // Endpoint para ver o carro
    @GetMapping("/listar")
    public List<Carromodel> vercarros() {
        return repo.findAll(); 
    }
 
    // Endpoint para criar o carro
    @PostMapping("/criar")
    public ResponseEntity<Carromodel> criarcarro(@RequestBody Carromodel carro) {
        Carromodel savedCarro = repo.save(carro); 
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCarro);  
    }

    // Endpoint para atualizar o carro
    @PutMapping("/atualizar")
    public ResponseEntity<Carromodel> atualizarcarro(@PathVariable Long id, @RequestBody Carromodel carro) {
        Optional<Carromodel> existingCarro = repo.findById(id);
        
        if (!existingCarro.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }

        carro.setId(id); 
        Carromodel updatedCarro = repo.save(carro);  
        return ResponseEntity.ok(updatedCarro); 
    }

    // Endpoint para excluir o carro
    @DeleteMapping("delete{id}")
    public ResponseEntity  <Carromodel> deletarCarro(@PathVariable Long id) {
        Optional<Carromodel> existingCarro = repo.findById(id);
        
        if (!existingCarro.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   
        }	

        repo.deleteById(id);   
        return ResponseEntity.noContent().build();  
    }
}
 
