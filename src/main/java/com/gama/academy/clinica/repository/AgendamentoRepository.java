package com.gama.academy.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gama.academy.clinica.model.Agendamento;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = "select * from tb_agendamento where paciente_id = :patientId", nativeQuery = true)
    public List<Agendamento> getAgendamentosByPatientId(Long patientId);
}
