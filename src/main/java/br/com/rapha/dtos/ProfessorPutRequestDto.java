package br.com.rapha.dtos;

import java.util.UUID;

import lombok.Data;
@Data
public class ProfessorPutRequestDto {

	private UUID id_professor;
	private String nome;
	private String telefone;
}
