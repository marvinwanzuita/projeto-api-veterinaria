package com.gama.academy.clinica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gama.academy.clinica.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

	@Query(value = "select * from tb_procedimento where id in (:ids)", nativeQuery = true)
	public List<Procedimento> getProcedimentos(List<Long> ids);
}
