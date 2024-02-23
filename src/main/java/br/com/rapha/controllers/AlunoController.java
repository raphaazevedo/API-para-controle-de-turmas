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

import br.com.rapha.dtos.AlunoPostRequestDto;
import br.com.rapha.dtos.AlunoPutRequestDto;
import br.com.rapha.entities.Aluno;
import br.com.rapha.repositories.AlunoRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/alunos")
public class AlunoController {

	@GetMapping
	public ResponseEntity<List<Aluno>> getAll()throws Exception{
		
		try {
			AlunoRepository alunoRepository = new AlunoRepository();
						
			List<Aluno> alunos = alunoRepository.findAllAluno();	
			
			if (alunos.size() == 0) {
				
				return ResponseEntity.status(204).body(null);	
				
			}
			
			return ResponseEntity.status(201).body(alunos);
			
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
		
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Aluno> getById(@PathVariable("id") UUID id)throws Exception {
		
		try {
			AlunoRepository alunoRepository = new AlunoRepository();
			
			Aluno aluno = alunoRepository.findByIdAluno(id);
			return ResponseEntity.status(201).body(aluno);
		}catch (Exception e) {
			return ResponseEntity.status(500).body(null);
		}
		
		
		
		
	}
	@PostMapping
	public ResponseEntity<String> post(@RequestBody @Valid AlunoPostRequestDto dto) {
		
		try {
			
			Aluno aluno = new Aluno();
			
			aluno.setId_aluno(UUID.randomUUID());
			aluno.setNome(dto.getNome());
			aluno.setCpf(dto.getCpf());
			aluno.setMatricula(dto.getMatricula());
			
			AlunoRepository alunoRepository = new AlunoRepository();
			alunoRepository.insertAluno(aluno);
			
			return ResponseEntity.status(201).body("Aluno cadastrado com sucesso!");
			
			
		} catch (Exception e) {

			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	@PutMapping
	public ResponseEntity<String> put(@RequestBody @Valid AlunoPutRequestDto dto) {
		
		try {
			AlunoRepository alunoRepository = new AlunoRepository();
			
			Aluno aluno = alunoRepository.findByIdAluno(dto.getId_aluno());
			
			if (aluno == null) {
				return ResponseEntity.status(400).body("Id do aluno não encontrado!");
				
			}
			
			aluno.setNome(dto.getNome());
			aluno.setCpf(dto.getCpf());
			aluno.setMatricula(dto.getMatricula());
			alunoRepository.updateAluno(aluno);
			
			return ResponseEntity.status(201).body("aluno atualizado com sucesso!");
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
		
		try {
			
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findByIdAluno(id);
			
			if (aluno == null) {
				throw new Exception("Aluno não encontrado!");
				
			}
			
			alunoRepository.deleteAluno(aluno);
			
			return ResponseEntity.status(201).body("Aluno excluido com sucesso!");
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		
		
		
	}
}
