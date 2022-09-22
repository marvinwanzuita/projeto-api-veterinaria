package com.gama.academy.clinica.controller;

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

import com.gama.academy.clinica.model.Tutor;
import com.gama.academy.clinica.service.TutorService;

@RestController()
@RequestMapping(value = "/tutores")
@CrossOrigin("*")
public class TutorController {

	@Autowired
	private TutorService tutorService;
	
	@GetMapping
	public ResponseEntity<List<Tutor>> getAll() {
		return ResponseEntity.ok(tutorService.getAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tutor> getById(@PathVariable Long id) {
		Tutor tutor = tutorService.getById(id);
		if(tutor !=null) {
			return ResponseEntity.ok(tutor);
			
		}else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Tutor tutor) throws Exception  {

		Tutor t = tutorService.save(tutor);

		if (tutor != null) {
			return ResponseEntity.ok(t);
		}
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tutor tutor)  {

		Tutor t = tutorService.update(id, tutor);

		if (tutor != null) {
			return ResponseEntity.ok(t);
		}
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		String msg = tutorService.delete(id);
		return ResponseEntity.ok(msg);
	}
	



}
