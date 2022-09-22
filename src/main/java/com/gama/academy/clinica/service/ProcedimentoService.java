package com.gama.academy.clinica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gama.academy.clinica.service.exception.ResourceNotFoundException;
import com.gama.academy.clinica.controller.exception.DatabaseException;
import com.gama.academy.clinica.dto.ProcedimentoDto;
import com.gama.academy.clinica.model.Procedimento;
import com.gama.academy.clinica.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Transactional(readOnly = true)
	public List<ProcedimentoDto> findAll(){
		List<Procedimento> lista = procedimentoRepository.findAll();
		
		return lista.stream()
					.map(x -> new ProcedimentoDto(x))
					.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProcedimentoDto findById(Long id) {
		Optional<Procedimento> objeto = procedimentoRepository.findById(id);
		
		Procedimento procedimento = objeto.orElseThrow(
				() -> new ResourceNotFoundException("Objeto não encontrado"));
		return new ProcedimentoDto(procedimento);
	}

	@Transactional
	public ProcedimentoDto insert(ProcedimentoDto procedimentoDto) {
		Procedimento entidade = new Procedimento();
		entidade.setDescricao(procedimentoDto.getDescricao());
		entidade = procedimentoRepository.save(entidade);
		return new ProcedimentoDto(entidade);
	}

	@Transactional
	public ProcedimentoDto update(Long id, ProcedimentoDto procedimentoDto) {
		
		try {
			// getReferenceById ele instancia um objeto provisório com o id do objeto,
			// 					sem ter que ir no banco de dados
			Procedimento entidade = procedimentoRepository.getReferenceById(id);
			entidade.setDescricao(procedimentoDto.getDescricao());
			entidade = procedimentoRepository.save(entidade);
			return new ProcedimentoDto(entidade);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado.");
		}
		
	}

	public void delete(Long id) {
		try {
			procedimentoRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado.");
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade, o Procedimento que você quer deletar esta salvo na Agenda, altere ou remova primeiro da Agenda.");
		}
		
	}
	
	
	
}
