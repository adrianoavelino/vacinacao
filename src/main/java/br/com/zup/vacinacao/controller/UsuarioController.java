package br.com.zup.vacinacao.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.vacinacao.controller.dto.UsuarioDto;
import br.com.zup.vacinacao.entity.Usuario;
import br.com.zup.vacinacao.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto dto, UriComponentsBuilder uriBuilder) {
		Usuario usuario = UsuarioDto.converter(dto);
		usuario = service.save(usuario);

		URI uri = uriBuilder.path("/v1/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
