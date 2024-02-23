package br.com.rapha.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> post(@RequestBody MatriculaPostRequestDto dto) throws Exception {

		try {

			Matricula matricula = new Matricula();

			matricula.setId_matricula(UUID.randomUUID());

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findByIdTurma(dto.getId_turma());

			if (turma == null) {
				return ResponseEntity.status(204).body("Turma não encontrada!");
			}

			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findByIdAluno(dto.getId_aluno());

			if (aluno == null) {
				return ResponseEntity.status(204).body("Aluno não encontrado!");
			}

			matricula.setTurma(turma);
			matricula.setAluno(aluno);
			matricula.setData_matricula(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_matricula()));

			MatriculaRepository matriculaRepository = new MatriculaRepository();

			matriculaRepository.insertMaticula(matricula);

			return ResponseEntity.status(201).body("Matricula criada com sucesso!");

		} catch (Exception e) {

			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {

		try {
			MatriculaRepository matriculaRepository = new MatriculaRepository();

			Matricula matricula = matriculaRepository.findByIdMatricula(id);

			if (matricula == null) {

				return ResponseEntity.status(204).body("Matricula não encontrada!");
			}

			matriculaRepository.deleteMatricula(matricula);

			return ResponseEntity.status(201).body("Matricula excluida com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@GetMapping
	public ResponseEntity<List<Matricula>> getAll() throws Exception {

		try {
			MatriculaRepository matriculaRepository = new MatriculaRepository();
			
			List<Matricula> matriculas = matriculaRepository.findAllMatricula();
			
			if(matriculas.size() == 0) {
				return ResponseEntity.status(204).body(null);
			}
			
			
			return ResponseEntity.status(null).body(matriculas);
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		

	}

	@GetMapping("{id}")
	public ResponseEntity<Matricula> getById(@PathVariable("id") UUID id) throws Exception{
		
		try {
			MatriculaRepository  matriculaRepository = new MatriculaRepository();
			
			Matricula matricula = matriculaRepository.findByIdMatricula(id);
			
			return ResponseEntity.status(201).body(matricula);
				
		}catch (Exception e) {
			return ResponseEntity.status(201).body(null);
		}
		
	}

}
