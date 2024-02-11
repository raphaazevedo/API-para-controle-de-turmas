package br.com.rapha.entities;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Matricula {

	private UUID id_matricula;
	private Turma turma;
	private Aluno aluno;
	private Date data_matricula;
}
