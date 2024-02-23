package br.com.rapha.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class TurmaPutRequestDot {
	
	private UUID turma_id;
	private String nome_turma;
	private String data_inicio;
	private String data_termino;
	private UUID professor_id;
}
