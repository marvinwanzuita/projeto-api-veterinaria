package com.gama.academy.clinica.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gama.academy.clinica.dto.AgendamentoDto;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.service.AgendamentoService;

@RestController
@RequestMapping(value = "/agendamentos")
@CrossOrigin("*")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;
	
	@GetMapping
	public ResponseEntity<List<AgendamentoDto>> findAll(){
		List<AgendamentoDto> lista = agendamentoService.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AgendamentoDto> findById(@PathVariable Long id){
		AgendamentoDto agendamentoDto = agendamentoService.findById(id);
		return ResponseEntity.ok().body(agendamentoDto);
	}
	
	@PostMapping
	public ResponseEntity<AgendamentoDto> insert(@RequestBody Agendamento agendamento){
		AgendamentoDto agendamentoDto = agendamentoService.save(agendamento);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(agendamentoDto.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(agendamentoDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AgendamentoDto> update(
			@PathVariable Long id,
			@RequestBody AgendamentoDto agendamentoDto){
		
		agendamentoDto = agendamentoService.update(id, agendamentoDto);
		return ResponseEntity.ok().body(agendamentoDto);	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		agendamentoService.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	
}
