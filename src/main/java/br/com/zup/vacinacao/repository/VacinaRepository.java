package br.com.zup.vacinacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.vacinacao.entity.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {

	Optional<Vacina> findByNomeIgnoreCase(String nomeVacina);

}
