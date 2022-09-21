package com.gama.academy.clinica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.StatusAgendamento;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoDto {

    private Long id;
    private StatusAgendamento statusAgendamento;
    private Double pesoPaciente;
    private PacienteDto paciente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT-3")
    private LocalDate dataAtendimento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
    private LocalTime horaAtendimento;

    public AgendamentoDto() {

    }

    public AgendamentoDto(Agendamento agendamento) {
        id = agendamento.getId();
        dataAtendimento = agendamento.getDataAtendimento();
        horaAtendimento = agendamento.getHoraAtendimento();
        pesoPaciente = agendamento.getPesoPaciente();
        statusAgendamento = agendamento.getStatusAgendamento();
        paciente = new PacienteDto(agendamento.getPaciente());
    }
}
