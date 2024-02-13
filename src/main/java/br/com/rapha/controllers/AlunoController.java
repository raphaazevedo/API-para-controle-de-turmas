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

import br.com.rapha.dtos.AlunoPostRequestDto;
import br.com.rapha.dtos.AlunoPutRequestDto;
import br.com.rapha.entities.Aluno;
import br.com.rapha.repositories.AlunoRepository;


@RestController
@RequestMapping(value = "/api/alunos")
public class AlunoController {

	@GetMapping
	public List<Aluno> getAll()throws Exception{
		
		AlunoRepository alunoRepository = new AlunoRepository();
		
		return alunoRepository.findAllAluno();
		
	}
	
	@GetMapping("{id}")
	public Aluno getById(@PathVariable("id") UUID id)throws Exception {
		
		
		
		AlunoRepository alunoRepository = new AlunoRepository();
		
		return alunoRepository.findByIdAluno(id);
		
		
	}
	@PostMapping
	public String post(@RequestBody AlunoPostRequestDto dto) {
		
		try {
			
			Aluno aluno = new Aluno();
			
			aluno.setId_aluno(UUID.randomUUID());
			aluno.setNome(dto.getNome());
			aluno.setCpf(dto.getCpf());
			aluno.setMatricula(dto.getMatricula());
			
			AlunoRepository alunoRepository = new AlunoRepository();
			alunoRepository.insertAluno(aluno);
			
			return "Aluno cadastrado com sucesso!";
			
			
		} catch (Exception e) {

			return e.getMessage();
		}
	}
	@PutMapping
	public String put(@RequestBody AlunoPutRequestDto dto) {
		
		try {
			AlunoRepository alunoRepository = new AlunoRepository();
			
			Aluno aluno = alunoRepository.findByIdAluno(dto.getId_aluno());
			
			if (aluno == null) {
				throw new Exception ("Id do aluno não encontrado!");
			}
			
			aluno.setNome(dto.getNome());
			aluno.setCpf(dto.getCpf());
			aluno.setMatricula(dto.getMatricula());
			alunoRepository.updateAluno(aluno);
			
			return "aluno atualizado com sucesso!";
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
	}
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") UUID id) {
		
		try {
			
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findByIdAluno(id);
			
			if (aluno == null) {
				throw new Exception("Aluno não encontrado!");
				
			}
			
			alunoRepository.deleteAluno(aluno);
			
			return "Aluno excluido com sucesso!";
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
		
	}
}
