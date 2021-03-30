package br.com.zup.vacinacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.vacinacao.entity.Vacina;
import br.com.zup.vacinacao.repository.VacinaRepository;

@Service
public class VacinaService {
	
	@Autowired
	private VacinaRepository repository;
	
	public Vacina save(Vacina vacina){
		return repository.save(vacina);
	}
	
}
