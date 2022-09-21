package com.gama.academy.clinica.service;

import com.gama.academy.clinica.controller.exception.ControllerNotFoundException;
import com.gama.academy.clinica.dto.AgendamentoDto;
import com.gama.academy.clinica.dto.PacienteDto;
import com.gama.academy.clinica.dto.ProcedimentoDto;
import com.gama.academy.clinica.dto.TutorDto;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Paciente;
import com.gama.academy.clinica.model.Procedimento;
import com.gama.academy.clinica.model.Tutor;
import com.gama.academy.clinica.service.exception.ResourceNotFoundException;
import com.gama.academy.clinica.service.exception.ViolationConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.academy.clinica.repository.AgendamentoRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TutorService tutorService;

    @Transactional(readOnly = true)
    public List<AgendamentoDto> getAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        List<AgendamentoDto> agendamentosDto = mapModelListToDto(agendamentos);
        return agendamentosDto;
    }

    @Transactional(readOnly = true)
    public List<AgendamentoDto> getAllByPatientId(Long id) {
        List<Agendamento> agendamentos = agendamentoRepository.getAgendamentosByPatientId(id);
        List<AgendamentoDto> agendamentosDto = mapModelListToDto(agendamentos);
        return agendamentosDto;
    }

    @Transactional
    public AgendamentoDto findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);

        if (!Objects.isNull(agendamento)) {
            return new AgendamentoDto(agendamento);
        }
        return null;
    }

    private List<AgendamentoDto> mapModelListToDto(List<Agendamento> agendamentos) {
        return agendamentos.stream()
                .map(agendamento -> new AgendamentoDto(agendamento))
                .collect(Collectors.toList());
    }

    @Transactional
    public AgendamentoDto save(Agendamento agendamento) {
        try {
            Paciente paciente = getPacienteByAgendamento(agendamento);
            agendamento.setPaciente(paciente);

            return new AgendamentoDto(agendamentoRepository.save(agendamento));
        } catch (ConstraintViolationException e) {
            throw new ViolationConstraintException(e.getMessage());
        }
    }

    @Transactional
    public AgendamentoDto update(Long id, Agendamento agendamento) {
        try {
            Agendamento entidade = agendamentoRepository.getReferenceById(id);
            entidade.setDataAtendimento(agendamento.getDataAtendimento());
            entidade.setHoraAtendimento(agendamento.getHoraAtendimento());
            entidade.setPesoPaciente(agendamento.getPesoPaciente());
            entidade.setStatusAgendamento(agendamento.getStatusAgendamento());

            Paciente paciente = getPacienteByAgendamento(agendamento);
            entidade.setPaciente(paciente);

            entidade = agendamentoRepository.save(entidade);
            return new AgendamentoDto(entidade);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Id não encontrado: " + id);
        }

    }

    @Transactional
    public String delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento != null) {
            agendamentoRepository.deleteById(id);
            return "Agendamento Excluido";
        }else {
            throw new ResourceNotFoundException(Paciente.class.getSimpleName());
        }
    }

    private Paciente getPacienteByAgendamento(Agendamento agendamento) {
        PacienteDto pacienteDto = pacienteService.findById(agendamento.getPacienteId());

        if (Objects.isNull(pacienteDto)) {
            throw new ResourceNotFoundException(Paciente.class.getSimpleName());
        }

        Paciente paciente = new Paciente();
        paciente.setId(pacienteDto.getId());
        paciente.setNome(pacienteDto.getNome());
        paciente.setDataNascimento(pacienteDto.getDataNascimento());
        paciente.setSexo(pacienteDto.getSexo());

        Tutor tutor = tutorService.getById(pacienteDto.getTutor().getId());
        paciente.setTutor(tutor);

        return paciente;
    }
}
