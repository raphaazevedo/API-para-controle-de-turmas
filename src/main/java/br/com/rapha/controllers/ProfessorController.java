package br.com.rapha.controllers;

import java.util.List;
import java.util.UUID;

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

@RestController
@RequestMapping(value = "/api/professores")
public class ProfessorController {
	
	@GetMapping
	public List<Professor> getAll()throws Exception{
		
		ProfessorRepository professorRepository = new ProfessorRepository();
				
		return professorRepository.findAllProfessor();
	}
	
	@GetMapping("{id}")
	public Professor getById(@PathVariable("id") UUID id)throws Exception {
		
		ProfessorRepository professorRepository = new ProfessorRepository();
		return professorRepository.findByIdProfessor(id);
	}
	
	@PostMapping
	public String post(@RequestBody ProfessorPostRequestDto dto)throws Exception {
		
		try {
			Professor professor = new Professor();
			
			professor.setId_professor(UUID.randomUUID());
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());
			
			
			ProfessorRepository professorRepository = new ProfessorRepository();	
			professorRepository.insertProfessor(professor);
			
			return "Professor incluido com sucesso!";
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	@PutMapping
	public String put(@RequestBody ProfessorPutRequestDto dto)throws Exception{
		
		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			
			Professor professor = professorRepository.findByIdProfessor(dto.getId_professor());
			
			if (professor == null) {
			
				throw new Exception("Professor não encontrado!");
			}
			
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());
			
			professorRepository.updateProfessor(professor);
			
			
			return "Professor atualizado com sucesso!";
						
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") UUID id)throws Exception {
		
		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findByIdProfessor(id);
			
			if(professor == null) {
				
				throw new Exception("Professor não encontrado!");

			}
			
			professorRepository.deleteProfessor(professor);
			
			return "Professor excluido com sucesso!";
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

}
