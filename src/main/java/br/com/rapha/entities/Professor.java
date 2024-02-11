package br.com.rapha.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Professor {

	private UUID id_professor;
	private String nome;
	private String telefone;
}
