package com.gama.academy.clinica.service;

import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.academy.clinica.dto.PacienteDto;
import com.gama.academy.clinica.model.Paciente;
import com.gama.academy.clinica.model.Tutor;
import com.gama.academy.clinica.repository.PacienteRepository;
import com.gama.academy.clinica.service.exception.ResourceNotFoundException;
import com.gama.academy.clinica.service.exception.ViolationConstraintException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private TutorService tutorService;

	public List<PacienteDto> getAll() {

		List<Paciente> pacientes = pacienteRepository.findAll();
		List<PacienteDto> pacientesDto = pacientes.stream().map(paciente -> new PacienteDto(paciente)).toList();
		return pacientesDto;
	}

	public PacienteDto findById(Long id) {
		Paciente paciente = pacienteRepository.findById(id).orElse(null);

		if (!Objects.isNull(paciente)) {
			return new PacienteDto(paciente);
		}
		return null;
	}

	public PacienteDto save(Paciente paciente) {

		Tutor tutor = tutorService.getById(paciente.getTutorId());
		if (!Objects.isNull(tutor)) {

			try {
				paciente.setTutor(tutor);
				Paciente pacienteSalvo = pacienteRepository.save(paciente);
				return new PacienteDto(pacienteSalvo);
			} catch (ConstraintViolationException e) {
				throw new ViolationConstraintException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(Tutor.class.getSimpleName());
		}
	}

	public PacienteDto update(Long id, Paciente newPatient) {

		Paciente oldPatient = pacienteRepository.findById(id).orElse(null);

		if (!Objects.isNull(oldPatient)) {

			try {
				
				Tutor tutor =  tutorService.getById(newPatient.getTutorId());
				
				if(!Objects.isNull(tutor)) {
					newPatient.setId(oldPatient.getId());
					newPatient.setTutor(tutor);
					newPatient = pacienteRepository.save(newPatient);
					
					return new PacienteDto(newPatient);
				}else {
					throw new ResourceNotFoundException(Tutor.class.getSimpleName());
				}
				
				
			} catch (ConstraintViolationException e) {
				throw new ViolationConstraintException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(Paciente.class.getSimpleName());
		}
	}

	public String delete(Long id) {

		Paciente p = pacienteRepository.findById(id).orElse(null);
		if (p != null) {
			System.out.println("Nome do Paciente: "+p.getNome());
//			Tutor tutor = tutorService.getById(p.getTutor().getId());
//			tutorService.update(tutor.getId(), tutor);
//			if(tutor.removePaciente(p)) {
//				System.out.println("Entrou aqui");
//				tutor.getPacientes().forEach(p2 -> System.out.println(p2));
//				pacienteRepository.delete(p);
//				return "Objeto Excluido";				
//			}else {
//				System.out.println("Deu ruim");
//				return null;
//			}
			
			pacienteRepository.deleteById(id);
			return "Objeto Excluido";	
		}else {
			throw new ResourceNotFoundException(Paciente.class.getSimpleName());
		}
	}

}
