package br.com.rapha.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rapha.dtos.TurmaPostRequestDto;
import br.com.rapha.dtos.TurmaPutRequestDot;
import br.com.rapha.entities.Professor;
import br.com.rapha.entities.Turma;
import br.com.rapha.repositories.ProfessorRepository;
import br.com.rapha.repositories.TurmaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/turmas")
public class TurmaController {
	@GetMapping
	public ResponseEntity<List<Turma>> getAll() throws Exception {
		
		try {
			TurmaRepository turmaRepository = new TurmaRepository();
			
			List<Turma> turmas = turmaRepository.findAllTurma();
			
			if (turmas.size() == 0) {
				return ResponseEntity.status(204).body(null);
			}
			
			return ResponseEntity.status(201).body(turmas);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
		
	}

	@GetMapping("{id}")
	public ResponseEntity<Turma> getById(@PathVariable("id") UUID id) throws Exception {

		try {
			TurmaRepository turmaRepository = new TurmaRepository();

			Turma turma = turmaRepository.findByIdTurma(id);
			
			if (turma == null) {
				return ResponseEntity.status(204).body(null);
			}
			
			return ResponseEntity.status(201).body(turma);
	
		}catch (Exception e) {
			return ResponseEntity.status(201).body(null);
		}
		
	}

	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid TurmaPostRequestDto dto) throws Exception {
		
		try {
			
			Turma turma = new Turma();
			
			turma.setId_turma(UUID.randomUUID());
			turma.setNome_turma(dto.getNome_turma());
			turma.setData_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_inicio()));
			turma.setData_termino(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_termino()));
			
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findByIdProfessor(dto.getProfessor_id());
			
			if (professor == null) {
				return ResponseEntity.status(204).body("Professor não encpontrado");
				
			}
			
			turma.setProfessor(professor);
			
			TurmaRepository turmaRepository = new TurmaRepository();
			
			turmaRepository.insertTurma(turma);
			
			return ResponseEntity.status(201).body("Turma cadastrada com sucesso!");

			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}

	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid TurmaPutRequestDot dto) throws Exception {
		
		try {
			
			TurmaRepository turmaRepository = new TurmaRepository();
			
			Turma turma = turmaRepository.findByIdTurma(dto.getTurma_id());
			
			if (turma == null) {
				return ResponseEntity.status(204).body("Id da turma não encontrado!");
				
			}
			
			ProfessorRepository professorRepository =new ProfessorRepository();
			
			Professor professor = professorRepository.findByIdProfessor(dto.getProfessor_id());
			
			if(professor == null) {
				return ResponseEntity.status(204).body("Professor não encontrado!");
			}
			
			
			//UUID idProfessor = professor.getId_professor();
			
			turma.setNome_turma(dto.getNome_turma());
			turma.setData_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_inicio()));
			turma.setData_termino(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_termino()));
			turma.getProfessor().setId_professor(dto.getProfessor_id());
			
			
			
			turmaRepository.updateTurma(turma);
			
			return ResponseEntity.status(201).body("Turma atualizada com sucesso!");

			
			
		} catch (Exception e) {
			return ResponseEntity.status(204).body(e.getMessage());
		}
		
		
		
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) throws Exception {
		
		try {
			
			TurmaRepository turmaRepository = new TurmaRepository();
			
			Turma turma = turmaRepository.findByIdTurma(id);
			
			if (turma == null) {
				return ResponseEntity.status(204).body("Turma não encontrada");
				
				
			}
			
			turmaRepository.deleteTurma(turma);
			
			return ResponseEntity.status(201).body("Turma deletada com sucesso!");
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
		

	}

}
