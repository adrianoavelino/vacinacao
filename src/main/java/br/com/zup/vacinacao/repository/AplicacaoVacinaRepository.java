package br.com.zup.vacinacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.vacinacao.entity.AplicacaoVacina;

public interface AplicacaoVacinaRepository extends JpaRepository<AplicacaoVacina, Long> {

}
