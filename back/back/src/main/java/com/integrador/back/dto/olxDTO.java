package com.integrador.back.dto;

import java.util.UUID;

public record olxDTO() {
	private UUID id;
	private String modelo;
	private int Ano;
	private String cor;
	private String preco;
	private String placa;
	 
}
