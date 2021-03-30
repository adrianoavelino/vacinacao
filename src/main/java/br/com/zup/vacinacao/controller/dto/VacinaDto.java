package br.com.zup.vacinacao.controller.dto;

import javax.validation.constraints.NotBlank;

import br.com.zup.vacinacao.entity.Vacina;

public class VacinaDto {

	@NotBlank(message = "não pode estar em branco")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static Vacina converter(VacinaDto dto) {
		return new Vacina(dto.nome);
	}

}
