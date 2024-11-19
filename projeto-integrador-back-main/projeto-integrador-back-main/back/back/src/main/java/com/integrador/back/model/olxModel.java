package com.integrador.back.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carros")
public class olxModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID Id;
	private  String modelo;
	private int Ano;
	private String cor;
	private String preco;
	private String placa;
	private String imagem;
	public UUID getId() {
		return Id;
	}
	public void setId(UUID id) {
		Id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAno() {
		return Ano;
	}
	public void setAno(int ano) {
		Ano = ano;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public olxModel(UUID id, String modelo, int ano, String cor, String preco, String placa, String imagem) {
		super();
		Id = id;
		this.modelo = modelo;
		Ano = ano;
		this.cor = cor;
		this.preco = preco;
		this.placa = placa;
		this.imagem = imagem;
	}
	
	

	
	
	
}

