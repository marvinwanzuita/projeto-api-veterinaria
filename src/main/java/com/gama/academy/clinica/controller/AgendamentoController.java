package com.gama.academy.clinica.controller;

import com.gama.academy.clinica.dto.AgendamentoDto;
import com.gama.academy.clinica.dto.PacienteDto;
import com.gama.academy.clinica.model.Agendamento;
import com.gama.academy.clinica.model.Paciente;
import com.gama.academy.clinica.repository.PacienteRepository;
import com.gama.academy.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.academy.clinica.service.AgendamentoService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<List<AgendamentoDto>> getAll() {
        List<AgendamentoDto> dtos = service.getAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        AgendamentoDto agendamentoDto = service.findById(id);

        if (!Objects.isNull(agendamentoDto)) {
            return ResponseEntity.ok(agendamentoDto);
        }
        return ResponseEntity.badRequest().body("Objeto não Encontrado");
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity getAllByPatientId(@PathVariable Long pacienteId) {
        if (pacienteId == null) {
            return ResponseEntity.badRequest().body("O id do paciente é obrigatório");
        }

        Boolean pacienteExiste = pacienteRepository.existsById(pacienteId);

        if (!pacienteExiste) {
            return ResponseEntity.badRequest().body("Não existe paciente com o id " + pacienteId);
        }

        return ResponseEntity.ok(service.getAllByPatientId(pacienteId));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Agendamento agendamento) throws Exception {
        return ResponseEntity.ok(service.save(agendamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Agendamento agendamento) {

        AgendamentoDto p = service.update(id, agendamento);

        if (!Objects.isNull(p)) {
            return ResponseEntity.ok(p);
        }

        return ResponseEntity.badRequest().body("Objeto não Encontrado");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String msg = service.delete(id);
        return ResponseEntity.ok(msg);
    }
}
