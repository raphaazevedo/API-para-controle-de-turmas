package br.com.rapha.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class MatriculaPostRequestDto {
	
	private UUID id_turma;
	private UUID id_aluno;
	private String data_matricula;
}
