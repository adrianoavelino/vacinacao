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

import br.com.zup.vacinacao.controller.dto.AplicacaoVacinaDto;
import br.com.zup.vacinacao.entity.AplicacaoVacina;
import br.com.zup.vacinacao.service.AplicacaoVacinaService;
import br.com.zup.vacinacao.service.UsuarioService;
import br.com.zup.vacinacao.service.VacinaService;

@RestController
@RequestMapping("/v1/aplicacaovacinas")
public class AplicacaoVacinaController {

	@Autowired
	private AplicacaoVacinaService service;
	
	@Autowired
	private UsuarioService serviceUsuario;
	
	@Autowired
	private VacinaService serviceVacina;

	@PostMapping
	public ResponseEntity<AplicacaoVacinaDto> cadastrar(@RequestBody @Valid AplicacaoVacinaDto dto, UriComponentsBuilder uriBuilder) {
		AplicacaoVacina aplicacaoVacina = AplicacaoVacinaDto.converter(dto, serviceVacina, serviceUsuario);
		service.save(aplicacaoVacina);

		URI uri = uriBuilder.path("/v1/aplicacaovacinas/{id}").buildAndExpand(aplicacaoVacina.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
