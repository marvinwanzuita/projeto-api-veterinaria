package com.gama.academy.clinica.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.academy.clinica.model.Tutor;
import com.gama.academy.clinica.repository.TutorRepository;
import com.gama.academy.clinica.service.exception.ControllerNotFoundException;
import com.gama.academy.clinica.service.exception.ViolationConstraintException;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;

	public List<Tutor> getAll() {
		
		return  tutorRepository.findAll();
	}

	public Tutor getById(Long id) {
		return tutorRepository.findById(id).orElse(null);
	}

	public Tutor save(Tutor tutor) {

		try {
			return tutorRepository.save(tutor);
		} catch (ConstraintViolationException e) {
			throw new ViolationConstraintException(e.getMessage());
		}
	}

	public Tutor update(Long id, Tutor newTutor) {

		Tutor oldTutor = getById(id);

		if (oldTutor != null) {
			newTutor.setId(id);
			return save(newTutor);
		} else {
			throw new ControllerNotFoundException(Tutor.class.getSimpleName());
		}
	}

	public String delete(Long id) {

		Tutor tutor = getById(id);

		if (tutor != null) {
			tutorRepository.deleteById(id);
			return "Objeto Excluido";
		} else {
			throw new ControllerNotFoundException(Tutor.class.getSimpleName());
		}

	}

}
