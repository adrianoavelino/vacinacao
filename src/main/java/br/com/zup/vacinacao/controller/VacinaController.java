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

import br.com.zup.vacinacao.controller.dto.VacinaDto;
import br.com.zup.vacinacao.entity.Vacina;
import br.com.zup.vacinacao.service.VacinaService;

@RestController
@RequestMapping("/v1/vacinas")
public class VacinaController {

	@Autowired
	private VacinaService service;

	@PostMapping
	public ResponseEntity<VacinaDto> cadastrar(@RequestBody @Valid VacinaDto dto, UriComponentsBuilder uriBuilder) {
		Vacina vacina = VacinaDto.converter(dto);
		service.save(vacina);

		URI uri = uriBuilder.path("/v1/vacinas/{id}").buildAndExpand(vacina.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
