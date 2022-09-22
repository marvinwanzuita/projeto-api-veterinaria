package com.gama.academy.clinica.model;

public enum StatusAgendamento {

	AGENDADO(0),
	ATENDIDO(1),
	NAO_COMPARECEU(2),
	CANCELADO(3),
	ADIADO(4);
	
	private Integer status;
	
	private StatusAgendamento(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

}
