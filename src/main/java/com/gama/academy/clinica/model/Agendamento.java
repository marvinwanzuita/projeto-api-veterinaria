package com.gama.academy.clinica.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "A Data de Nascimento é Obrigatória!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT-3")
	private LocalDate dataAtendimento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
	private LocalTime horaAtendimento;

	@NotNull(message = "Status do agendamento é obrigatório")
	@Enumerated(EnumType.ORDINAL)
	private StatusAgendamento statusAgendamento;

	private Double pesoPaciente;
	
	@OneToOne
	@JoinColumn(name = "paciente_id")
	@JsonIgnore
	private Paciente paciente;

	@Transient
	private Long pacienteId;
	
	@OneToMany
	private List<Procedimento> procedimentos;

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
