package br.com.rapha.controllers;

import java.text.SimpleDateFormat;
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

import br.com.rapha.dtos.TurmaPostRequestDto;
import br.com.rapha.dtos.TurmaPutRequestDot;
import br.com.rapha.entities.Professor;
import br.com.rapha.entities.Turma;
import br.com.rapha.repositories.ProfessorRepository;
import br.com.rapha.repositories.TurmaRepository;

@RestController
@RequestMapping(value = "/api/turmas")
public class TurmaController {
	@GetMapping
	public List<Turma> getAll() throws Exception {
		TurmaRepository turmaRepository = new TurmaRepository();

		return turmaRepository.findAllTurma();
	}

	@GetMapping("{id}")
	public Turma getById(@PathVariable("id") UUID id) throws Exception {

		TurmaRepository turmaRepository = new TurmaRepository();

		return turmaRepository.findByIdTurma(id);

	}

	@PostMapping
	public String post(@RequestBody TurmaPostRequestDto dto) throws Exception {
		
		try {
			
			Turma turma = new Turma();
			
			turma.setId_turma(UUID.randomUUID());
			turma.setNome_turma(dto.getNome_turma());
			turma.setData_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_inicio()));
			turma.setData_termino(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_termino()));
			
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findByIdProfessor(dto.getProfessor_id());
			
			if (professor == null) {
				throw new Exception("Professor não encpontrado");
			}
			
			turma.setProfessor(professor);
			
			TurmaRepository turmaRepository = new TurmaRepository();
			
			turmaRepository.insertTurma(turma);
			
			return "Turma cadastrada com sucesso!";

			
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}

	@PutMapping
	public String put(@RequestBody TurmaPutRequestDot dto) throws Exception {
		
		try {
			
			TurmaRepository turmaRepository = new TurmaRepository();
			
			Turma turma = turmaRepository.findByIdTurma(dto.getTurma_id());
			
			if (turma == null) {
				throw new Exception("Id da turma não encontrado!");
			}
			
			ProfessorRepository professorRepository =new ProfessorRepository();
			
			Professor professor = professorRepository.findByIdProfessor(dto.getProfessor_id());
			
			if(professor == null) {
				throw new Exception("Professor não encontrado!");
			}
			
			
			//UUID idProfessor = professor.getId_professor();
			
			turma.setNome_turma(dto.getNome_turma());
			turma.setData_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_inicio()));
			turma.setData_termino(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData_termino()));
			turma.getProfessor().setId_professor(dto.getProfessor_id());
			
			
			
			turmaRepository.updateTurma(turma);
			
			return "Turma atualizada com sucesso!";

			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		
		
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") UUID id) throws Exception {
		
		try {
			
			TurmaRepository turmaRepository = new TurmaRepository();
			
			Turma turma = turmaRepository.findByIdTurma(id);
			
			if (turma == null) {
				throw new Exception ("Turma não encontrada");
				
			}
			
			turmaRepository.deleteTurma(turma);
			
			return "Turma deletada com sucesso!";
			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		

	}

}
