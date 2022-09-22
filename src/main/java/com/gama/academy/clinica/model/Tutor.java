package com.gama.academy.clinica.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "tb_tutor")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é Obrigatório!")
	private String nome;
	
	@NotBlank(message = "Email é Obrigatório!")
	@Email(message = "Email Inválido!")
	private String email;
	
	@NotBlank(message = "Telefone é Obrigatório!")
	private String telefone;

	@OneToMany(mappedBy = "tutor", targetEntity = Paciente.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Paciente> pacientes;

	public Tutor() {
		this.pacientes = new ArrayList<>();
	}

	public void addPaciente(Paciente paciente) {
		this.pacientes.add(paciente);
	}
	
	public Boolean removePaciente(Paciente p) {
		return pacientes.remove(p);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutor other = (Tutor) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
