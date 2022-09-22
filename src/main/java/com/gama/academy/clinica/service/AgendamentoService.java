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

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gama.academy.clinica.controller.exception.DatabaseException;
import com.gama.academy.clinica.dto.AgendamentoDto;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Paciente;
import com.gama.academy.clinica.model.Procedimento;
import com.gama.academy.clinica.model.StatusAgendamento;
import com.gama.academy.clinica.repository.AgendamentoRepository;
import com.gama.academy.clinica.repository.PacienteRepository;
import com.gama.academy.clinica.repository.ProcedimentoRepository;
import com.gama.academy.clinica.service.exception.ResourceNotFoundException;
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
		Agendamento entidade = objeto.orElseThrow((() -> new ResourceNotFoundException("id")));
		return new AgendamentoDto(entidade);
	}

	@Transactional
	public AgendamentoDto save(Agendamento agendamento) {

		if (agendamento.getPacienteId() == null) {
			throw new NullPointerException("O ID de Paciente não pode ser NULO!!");
		} else {
			Paciente paciente = pacienteRepository.findById(agendamento.getPacienteId()).orElse(null);

			if (!Objects.isNull(paciente)) {

				List<Procedimento> procedimentos = procedimentoRepository
						.getProcedimentos(agendamento.getProcedimentosIds());

				if (!procedimentos.isEmpty()) {

					procedimentos.forEach(p -> System.out.println(p.getDescricao()));

					try {

						if (agendamento.getDataAtendimento() != null) {
							LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

							boolean isDataAntiga = ChronoUnit.DAYS.between(agendamento.getDataAtendimento(), today) > 0
									? true
									: false;

							boolean isConsultaNaoEfetuada = agendamento
									.getStatusAgendamento() != StatusAgendamento.AGENDADO ? true : false;

							if (isDataAntiga == false || (isDataAntiga && isConsultaNaoEfetuada)) {

								if (agendamento.getStatusAgendamento() == null) {
									throw new NullPointerException("O status de Agendamento é Obrigatório!");
								}

								Agendamento entidade = new Agendamento();

								entidade.setDataAtendimento(agendamento.getDataAtendimento());
								entidade.setHoraAtendimento(agendamento.getHoraAtendimento());
								entidade.setStatusAgendamento(agendamento.getStatusAgendamento());
								entidade.setPesoPaciente(agendamento.getPesoPaciente());
								entidade.setPaciente(paciente);
								entidade.setProcedimentos(procedimentos);

								entidade = agendamentoRepository.save(entidade);

								return new AgendamentoDto(entidade);
							} else {
								throw new InvalidDateException(agendamento.getDataAtendimento().toString());
							}
						} else {
							throw new NullPointerException("A Data de Agendamento é Obrigatória!");
						}

					} catch (ConstraintViolationException e) {
						throw new ViolationConstraintException(e.getMessage());
					}

				} else {
					throw new ResourceNotFoundException(Procedimento.class.getSimpleName());
				}
			} else {
				throw new ResourceNotFoundException(Paciente.class.getSimpleName());
			}
		}

	}

	@Transactional
	public AgendamentoDto update(Long id, Agendamento agendamento) {
		try {
			Agendamento entidade = agendamentoRepository.getReferenceById(id);
			entidade.setDataAtendimento(agendamento.getDataAtendimento());
			entidade.setHoraAtendimento(agendamento.getHoraAtendimento());
			entidade.setStatusAgendamento(agendamento.getStatusAgendamento());
			entidade.setPesoPaciente(agendamento.getPesoPaciente());

			List<Procedimento> procedimentos = procedimentoRepository
					.getProcedimentos(agendamento.getProcedimentosIds());

			entidade.setProcedimentos(procedimentos);
			return new AgendamentoDto(entidade);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado");
		} catch (ConstraintViolationException e2) {
			throw new ViolationConstraintException(e2.getMessage());
		}

	}

	public void delete(Long id) {

		try {
			agendamentoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}

	}

}
