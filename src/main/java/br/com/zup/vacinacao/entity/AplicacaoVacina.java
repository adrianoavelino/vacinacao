package br.com.zup.vacinacao.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aplicacao_vacinas")
public class AplicacaoVacina implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne 
	@JoinColumn(name = "vacina_id")
	@NotNull
	private Vacina vacina;
	
	@ManyToOne 
	@JoinColumn(name = "usuario_id")
	@NotNull
	private Usuario usuario;
	
	@NotNull
	private LocalDate data;
	
	public AplicacaoVacina(Vacina vacina, Usuario usuario, LocalDate data) {
		this.data = data;
		this.vacina = vacina;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AplicacaoVacina [vacina=" + vacina.getNome() + ", usuario=" + usuario.getEmail() + ", data=" + data
				+ "]";
	}

}
