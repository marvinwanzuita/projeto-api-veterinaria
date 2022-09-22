package com.gama.academy.clinica.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gama.academy.clinica.controller.exception.DatabaseException;
import com.gama.academy.clinica.dto.AgendamentoDto;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Paciente;
import com.gama.academy.clinica.model.Procedimento;
import com.gama.academy.clinica.repository.AgendamentoRepository;
import com.gama.academy.clinica.repository.PacienteRepository;
import com.gama.academy.clinica.repository.ProcedimentoRepository;
import com.gama.academy.clinica.service.exception.ControllerNotFoundException;
import com.gama.academy.clinica.service.exception.InvalidDateException;
import com.gama.academy.clinica.service.exception.ViolationConstraintException;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@Transactional(readOnly = true)
	public List<AgendamentoDto> findAll() {
		List<Agendamento> lista = agendamentoRepository.findAll();
		return lista.stream().map(x -> new AgendamentoDto(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AgendamentoDto findById(Long id) {
		Optional<Agendamento> objeto = agendamentoRepository.findById(id);
		Agendamento entidade = objeto.orElseThrow((() -> new ControllerNotFoundException("id")));
		return new AgendamentoDto(entidade);
	}

	@Transactional
	public AgendamentoDto insert(Agendamento agendamento) {

		Paciente paciente = pacienteRepository.findById(agendamento.getPacienteId()).orElse(null);

		if (!Objects.isNull(paciente)) {

			List<Procedimento> procedimentos = procedimentoRepository
					.getProcedimentos(agendamento.getProcedimentosIds());

			if (!procedimentos.isEmpty()) {

				procedimentos.forEach(p -> System.out.println(p.getDescricao()));

				try {

					LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

					boolean isDataAntiga = ChronoUnit.DAYS.between(agendamento.getDataAtendimento(), today) > 0 ? true
							: false;
					System.out.println("QNT dias: "+ ChronoUnit.DAYS.between(agendamento.getDataAtendimento(), today));
					
					if(isDataAntiga == false) {						
						Agendamento entidade = new Agendamento();
						
						entidade.setDataAtendimento(agendamento.getDataAtendimento());
						entidade.setHoraAtendimento(agendamento.getHoraAtendimento());
						entidade.setStatusAgendamento(agendamento.getStatusAgendamento());
						entidade.setPesoPaciente(agendamento.getPesoPaciente());
						entidade.setPaciente(paciente);
						entidade.setProcedimentos(procedimentos);
						
						entidade = agendamentoRepository.save(entidade);
						
						return new AgendamentoDto(entidade);
					}else {
						throw new InvalidDateException(agendamento.getDataAtendimento().toString());
					}

				} catch (ConstraintViolationException e) {
					throw new ViolationConstraintException(e.getMessage());
				}

			} else {
				throw new ControllerNotFoundException(Procedimento.class.getSimpleName());
			}
		} else {
			throw new ControllerNotFoundException(Paciente.class.getSimpleName());
		}

	}

	@Transactional
	public AgendamentoDto update(Long id, AgendamentoDto agendamentoDto) {
		try {
			Agendamento entidade = agendamentoRepository.getReferenceById(id);
			entidade.setDataAtendimento(agendamentoDto.getDataAtendimento());
			entidade.setHoraAtendimento(agendamentoDto.getHoraAtendimento());
			entidade.setStatusAgendamento(agendamentoDto.getStatusAgendamento());
			entidade.setPesoPaciente(agendamentoDto.getPesoPaciente());
			return new AgendamentoDto(entidade);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id não encontrado");
		}

	}

	public void delete(Long id) {

		try {
			agendamentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("Id não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}

	}

}
