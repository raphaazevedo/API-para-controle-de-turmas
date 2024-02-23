package br.com.rapha.controllers;

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

import br.com.rapha.dtos.ProfessorPostRequestDto;
import br.com.rapha.dtos.ProfessorPutRequestDto;
import br.com.rapha.entities.Professor;
import br.com.rapha.repositories.ProfessorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/professores")
public class ProfessorController {
	
	@GetMapping
	public ResponseEntity<List<Professor>> getAll()throws Exception{
		
		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			
			List<Professor> professores = professorRepository.findAllProfessor();
			
			if(professores.size() == 0) {
				return ResponseEntity.status(204).body(null);
			}
			
			return ResponseEntity.status(201).body(professores);
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
	}
	
	@GetMapping("{id}")
	public Professor getById(@PathVariable("id") UUID id)throws Exception {
		
		ProfessorRepository professorRepository = new ProfessorRepository();
		return professorRepository.findByIdProfessor(id);
	}
	
	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid ProfessorPostRequestDto dto)throws Exception {
		
		try {
			Professor professor = new Professor();
			
			professor.setId_professor(UUID.randomUUID());
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());
			
			
			ProfessorRepository professorRepository = new ProfessorRepository();	
			professorRepository.insertProfessor(professor);
			
			return ResponseEntity.status(201).body("Professor incluido com sucesso!");
			
			
		} catch (Exception e) {
			return ResponseEntity.status(201).body(e.getMessage());
		}
		
	}
	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid ProfessorPutRequestDto dto)throws Exception{
		
		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			
			Professor professor = professorRepository.findByIdProfessor(dto.getId_professor());
			
			if (professor == null) {
			
				return ResponseEntity.status(204).body("Professor não encontrado!");
				
			}
			
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());
			
			professorRepository.updateProfessor(professor);
			
			
			return ResponseEntity.status(201).body("Professor atualizado com sucesso!");
						
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id)throws Exception {
		
		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findByIdProfessor(id);
			
			if(professor == null) {
				
				return ResponseEntity.status(204).body("Professor não encontrado!");
				

			}
			
			professorRepository.deleteProfessor(professor);
			
			return ResponseEntity.status(201).body("Professor excluido com sucesso!");
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
	}

}
