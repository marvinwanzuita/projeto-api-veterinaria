package com.gama.academy.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Paciente;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	List<Agendamento> findByPaciente(Paciente paciente);
	
	@Query(value = "select * from tb_agendamento where paciente_id in (:ids)", nativeQuery = true)
	public List<Agendamento> getAgendamentos(List<Long> ids);
	
	@Query(value = "delete FROM TB_AGENDAMENTO  where paciente_id in (:ids)", nativeQuery = true)
	public void deleteAllAgendamento(List<Long> ids);

}
