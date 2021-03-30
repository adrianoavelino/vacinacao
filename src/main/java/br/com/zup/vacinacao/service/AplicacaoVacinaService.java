package br.com.zup.vacinacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.vacinacao.entity.AplicacaoVacina;
import br.com.zup.vacinacao.repository.AplicacaoVacinaRepository;

@Service
public class AplicacaoVacinaService {

	@Autowired
	private AplicacaoVacinaRepository repository;
	
	public AplicacaoVacina save(AplicacaoVacina aplicacaoVacina){
		return repository.save(aplicacaoVacina);
	}
	
}
