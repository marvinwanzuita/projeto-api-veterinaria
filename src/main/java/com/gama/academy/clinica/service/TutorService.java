package com.gama.academy.clinica.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gama.academy.clinica.controller.exception.DatabaseException;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Paciente;
import com.gama.academy.clinica.model.Tutor;
import com.gama.academy.clinica.repository.AgendamentoRepository;
import com.gama.academy.clinica.repository.TutorRepository;
import com.gama.academy.clinica.service.exception.ResourceNotFoundException;
import com.gama.academy.clinica.service.exception.ViolationConstraintException;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Tutor> getAll() {

		return tutorRepository.findAll();
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
			throw new ResourceNotFoundException(Tutor.class.getSimpleName());
		}
	}

	public String delete(Long id) {

		Tutor tutor = getById(id);

		if (tutor != null) {
			try {
				List<Long> ids = new ArrayList<>();

				for (Paciente paciente : tutor.getPacientes()) {
					ids.add(paciente.getId());
				}
				List<Agendamento> agendamentos = agendamentoRepository.getAgendamentos(ids);

				if (agendamentos.size() > 0) {
					throw new DatabaseException(
							"Impossivel remover, pois o mesmo esta salvo na Agenda, Remova-o Primeiro da Agenda para depois deletar");

				}
				tutorRepository.deleteById(id);
				return "Objeto Excluido";

			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(
						"Impossivel remover, pois o mesmo esta salvo na Agenda, Remova-o Primeiro da Agenda para depois deletar");
			}

		} else {
			throw new ResourceNotFoundException(Tutor.class.getSimpleName());
		}

	}

}
