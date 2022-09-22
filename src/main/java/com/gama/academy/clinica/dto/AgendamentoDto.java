package com.gama.academy.clinica.dto;

import java.time.LocalDate;
import java.util.List;

import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Procedimento;
import com.gama.academy.clinica.model.StatusAgendamento;

import lombok.Data;

@Data
public class AgendamentoDto {

	private Long id;
	private LocalDate dataAtendimento;
	private String horaAtendimento;
	private StatusAgendamento statusAgendamento;
	private Double pesoPaciente;

	private PacienteDto paciente;

	private List<Procedimento> procedimentos;

	public AgendamentoDto() {
	}

	public AgendamentoDto(Agendamento agendamento) {
		id = agendamento.getId();
		dataAtendimento = agendamento.getDataAtendimento();
		horaAtendimento = agendamento.getHoraAtendimento();
		statusAgendamento = agendamento.getStatusAgendamento();
		pesoPaciente = agendamento.getPesoPaciente();
		if (agendamento.getPaciente() != null) {
			paciente = new PacienteDto(agendamento.getPaciente());
		}
		if (agendamento.getProcedimentos() != null) {
			procedimentos = agendamento.getProcedimentos();
		}
	}

}
