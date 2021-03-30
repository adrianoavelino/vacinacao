package br.com.zup.vacinacao.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.vacinacao.entity.AplicacaoVacina;
import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.entity.Vacina;
import br.com.zup.vacinacao.service.UsuarioService;
import br.com.zup.vacinacao.service.VacinaService;

public class AplicacaoVacinaDto {

	@NotBlank(message = "Não pode estar em branco")
	private String nomeVacina;

	@NotEmpty(message = "não pode estar vazio")
	@Email(message = "deve ser um email válido")
	private String email;
	
	@NotNull(message = "não pode estar vazio")
	private LocalDate data;

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public static AplicacaoVacina converter(AplicacaoVacinaDto dto, VacinaService serviceVacina,
			UsuarioService serviceUsuario) {
		Vacina vacina = serviceVacina.findByNome(dto.nomeVacina);
		Usuario usuario = serviceUsuario.findByEmail(dto.email);
		return new AplicacaoVacina(vacina, usuario, dto.data);
	}
}
