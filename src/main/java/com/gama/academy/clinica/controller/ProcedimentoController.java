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

import com.gama.academy.clinica.dto.ProcedimentoDto;
import com.gama.academy.clinica.model.Procedimento;
import com.gama.academy.clinica.service.ProcedimentoService;

@RestController
@RequestMapping(value = "/procedimentos")
@CrossOrigin("*")
public class ProcedimentoController {
	
	@Autowired
	private ProcedimentoService procedimentoService;
	
	@GetMapping
	public ResponseEntity<List<ProcedimentoDto>> findAll(){
		List<ProcedimentoDto> lista = procedimentoService.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProcedimentoDto> findById(@PathVariable Long id){
		ProcedimentoDto procedimentoDto = procedimentoService.findById(id);
		return ResponseEntity.ok().body(procedimentoDto);
	}
	
	@PostMapping
	public ResponseEntity<ProcedimentoDto> insert(@RequestBody ProcedimentoDto procedimentoDto){
		
		procedimentoDto = procedimentoService.insert(procedimentoDto);
		
		// Para retornar o código 201 Created e demonstrar que o recurso foi criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(procedimentoDto.getId()).toUri();
				
		return ResponseEntity.created(uri).body(procedimentoDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProcedimentoDto> update(
			@PathVariable Long id, 
			@RequestBody ProcedimentoDto procedimentoDto ){
		
		procedimentoDto = procedimentoService.update(id, procedimentoDto);
		return ResponseEntity.ok().body(procedimentoDto);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id){
		procedimentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	

}
