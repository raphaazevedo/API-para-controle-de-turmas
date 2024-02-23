package br.com.rapha.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TurmaPostRequestDto {

	@NotBlank(message = "O nome da turma não pode ser vazio!")
	private String nome_turma;
	
	@Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}", message = "A data deve ter o fomato 09/09/9999")
	@NotBlank(message = "O campo data de inicio não pode ser vazio!")
	private String data_inicio;
	@Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}", message = "A data deve ter o fomato 09/09/9999")
	@NotBlank(message = "O campo data de termino não pode ser vazio!")
	private String data_termino;
	private UUID professor_id;
}
