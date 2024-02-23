package br.com.rapha.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoPutRequestDto {
	
	
	private UUID id_aluno;
	
	@Size(min = 8, max = 100, message = "Informe o nome de 8 A 100 caracteres!")
	@NotBlank(message = "o Campo nome não pode ser vazio!")
	private String nome;
	@NotBlank(message = "O campo matricula não pod ser vazio!")
	@Size(max = 5)
	private String matricula;
	@NotBlank(message = "O campo cpf não pode ser vazio!")
	@Size(max = 14)
	private String cpf;


}
