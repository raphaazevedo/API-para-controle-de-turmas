package br.com.rapha.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Aluno {

	private UUID id_aluno;
	private String nome;
	private String matricula;
	private String cpf;
}
