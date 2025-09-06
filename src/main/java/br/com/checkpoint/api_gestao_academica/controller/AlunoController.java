package br.com.checkpoint.api_gestao_academica.controller;

import br.com.checkpoint.api_gestao_academica.model.dto.AlunoDetailsDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.AlunoListDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.AlunoRegisterDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.AlunoUpdateDTO;
import br.com.checkpoint.api_gestao_academica.model.Aluno;
import br.com.checkpoint.api_gestao_academica.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<AlunoDetailsDTO> create(@RequestBody @Valid AlunoRegisterDTO data, UriComponentsBuilder uriBuilder) {
        var aluno = new Aluno(data);
        repository.save(aluno);
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDetailsDTO(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<AlunoListDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(AlunoListDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AlunoDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid AlunoUpdateDTO data) {
        var aluno = repository.getReferenceById(id);
        aluno.updateInfo(data);
        return ResponseEntity.ok(new AlunoDetailsDTO(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var aluno = repository.getReferenceById(id);
        aluno.delete();
        return ResponseEntity.noContent().build();
    }
}
