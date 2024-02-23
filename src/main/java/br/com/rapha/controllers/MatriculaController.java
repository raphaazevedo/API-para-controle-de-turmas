package br.com.rapha.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rapha.dtos.MatriculaPostRequestDto;
import br.com.rapha.entities.Aluno;
import br.com.rapha.entities.Matricula;
import br.com.rapha.entities.Turma;
import br.com.rapha.repositories.AlunoRepository;
import br.com.rapha.repositories.MatriculaRepository;
import br.com.rapha.repositories.TurmaRepository;

@RestController
@RequestMapping(value = "/api/matriculas")
public class MatriculaController {

	@PostMapping
	public String post(@RequestBody MatriculaPostRequestDto dto) throws Exception {

		try {

			Matricula matricula = new Matricula();

			matricula.setId_matricula(UUID.randomUUID());

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findByIdTurma(dto.getId_turma());

			if (turma == null) {
				throw new Exception("Turma não encontrada!");
			}

			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findByIdAluno(dto.getId_aluno());

			if (aluno == null) {
				throw new Exception("Aluno não encontrado!");
			}

			matricula.setTurma(turma);
			matricula.setAluno(aluno);
			matricula.setData_matricula(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_matricula()));

			MatriculaRepository matriculaRepository = new MatriculaRepository();

			matriculaRepository.insertMaticula(matricula);

			return "Matricula criada com sucesso!";

		} catch (Exception e) {

			return e.getMessage();
		}

	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") UUID id) throws Exception {

		try {
			MatriculaRepository matriculaRepository = new MatriculaRepository();

			Matricula matricula = matriculaRepository.findByIdMatricula(id);

			if (matricula == null) {

				return "Matricula não encontrada!";
			}

			matriculaRepository.deleteMatricula(matricula);

			return "Matricula excluida com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@GetMapping
	public List<Matricula> getAll() throws Exception {

		MatriculaRepository matriculaRepository = new MatriculaRepository();

		return matriculaRepository.findAllMatricula();

	}

	@GetMapping("{id}")
	public Matricula getById(@PathVariable("id") UUID id) throws Exception{
		
		MatriculaRepository  matriculaRepository = new MatriculaRepository();
		
		return matriculaRepository.findByIdMatricula(id);
		
	}

}
