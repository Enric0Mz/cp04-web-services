package br.com.checkpoint.api_gestao_academica.controller;

import br.com.checkpoint.api_gestao_academica.model.dto.InstructorDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.InstructorListDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.InstructorUpdateDTO;
import br.com.checkpoint.api_gestao_academica.model.Instructor;
import br.com.checkpoint.api_gestao_academica.repository.InstructorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<InstructorListDTO> create(@RequestBody @Valid InstructorDTO data, UriComponentsBuilder uriBuilder) {
        Instructor instructor = new Instructor(data);
        repository.save(instructor);
        URI uri = uriBuilder.path("/instructors/{id}").buildAndExpand(instructor.getId()).toUri();
        return ResponseEntity.created(uri).body(new InstructorListDTO(instructor));
    }

    @GetMapping
    public ResponseEntity<Page<InstructorListDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        Page<Instructor> instructorPage = repository.findAllByAtivoTrue(pageable);
        Page<InstructorListDTO> dtoList = instructorPage.map(InstructorListDTO::new);
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<InstructorListDTO> update(@PathVariable Long id, @RequestBody @Valid InstructorUpdateDTO data) {
        var instructor = repository.getReferenceById(id);
        instructor.updateInfo(data);
        return ResponseEntity.ok(new InstructorListDTO(instructor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var instructor = repository.getReferenceById(id);
        instructor.delete();
        return ResponseEntity.noContent().build();
    }
}