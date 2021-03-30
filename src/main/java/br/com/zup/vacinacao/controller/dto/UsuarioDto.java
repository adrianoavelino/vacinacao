package br.com.zup.vacinacao.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.zup.vacinacao.entity.Usuario;

public class UsuarioDto {

	@NotEmpty(message = "não pode estar vazio")
	@NotBlank(message = "não pode estar em branco")
	private String nome;

	@NotEmpty(message = "não pode estar vazio")
	@Email(message = "deve ser um email válido")
	private String email;

	@NotEmpty(message = "não pode estar vazio")
	@CPF(message = "deve utilizar um CPF válido")
	private String cpf;

	@NotNull(message = "não pode ser nulo")
	private LocalDate dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public static Usuario converter(UsuarioDto dto) {
		return new Usuario(dto.nome, dto.email, dto.cpf, dto.dataNascimento);
	}

}
