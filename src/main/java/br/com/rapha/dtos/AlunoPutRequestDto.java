package br.com.rapha.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AlunoPutRequestDto {
	
	
	private UUID id_aluno;
	private String nome;
	private String matricula;
	private String cpf;


}
