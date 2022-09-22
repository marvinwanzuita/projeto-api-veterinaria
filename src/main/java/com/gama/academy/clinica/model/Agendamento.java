package com.gama.academy.clinica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "A Data de Atendimento é Obrigatória!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT-3")
	private LocalDate dataAtendimento;
	
	@NotBlank(message = "O Horário de Atendimento é Obrigatória!")
	private String horaAtendimento;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusAgendamento statusAgendamento;
	
	@NotNull(message = "O Peso do Paciente é Obrigatório!")	
	private Double pesoPaciente;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Paciente paciente;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Procedimento> procedimentos;
	
	@Transient
	//@NotNull(message = "O ID do Paciente é Obrigatório!")	
	private Long pacienteId;
	
	@Transient
	//@NotNull(message = "O(s) Procedimento(s) são Obrigatórios!")	
	private List<Long> procedimentosIds;

	public Agendamento() {
		procedimentos = new ArrayList<>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
