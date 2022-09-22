package com.gama.academy.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.academy.clinica.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

}
