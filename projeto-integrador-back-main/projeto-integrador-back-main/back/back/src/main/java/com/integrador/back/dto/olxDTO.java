package com.integrador.back.dto;

import java.util.UUID;

public record olxDTO (UUID Id,String modelo,int Ano,String cor,String preco, String placa,String imagem) {
	  
}
