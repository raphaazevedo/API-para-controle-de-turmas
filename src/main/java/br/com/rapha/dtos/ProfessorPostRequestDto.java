package br.com.rapha.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfessorPostRequestDto {
	
	@Size(min = 8, max = 100, message = "Informe o nome de 8 A 100 caracteres!")
	@NotBlank(message = "o Campo nome não pode ser vazio!")
	private String nome;
	
	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}", message = "Informe o telefone no formato (99) 99999-9999")
	@NotBlank(message = "Informe um numero de telefone!")
	private String telefone;
}
