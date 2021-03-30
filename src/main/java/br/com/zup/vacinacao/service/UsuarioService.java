package br.com.zup.vacinacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario save(Usuario usuario){
		return repository.save(usuario);
	}

}
