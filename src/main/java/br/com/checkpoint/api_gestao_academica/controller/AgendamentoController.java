package br.com.checkpoint.api_gestao_academica.controller;

import br.com.checkpoint.api_gestao_academica.model.dto.AgendamentoDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.AgendamentoDetailsDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.CancelamentoAgendamentoDTO;
import br.com.checkpoint.api_gestao_academica.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity<AgendamentoDetailsDTO> agendar(@RequestBody @Valid AgendamentoDTO dados) {
        var agendamento = agendamentoService.agendar(dados);
        return ResponseEntity.ok(new AgendamentoDetailsDTO(agendamento));
    }

    @PostMapping("/cancelar")
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancelamentoAgendamentoDTO dados) {
        agendamentoService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}

