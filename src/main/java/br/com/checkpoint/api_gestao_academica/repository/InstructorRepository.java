package br.com.checkpoint.api_gestao_academica.repository;

import br.com.checkpoint.api_gestao_academica.model.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
    Page<Instructor> findAllByAtivoTrue(Pageable pageable);

    List<Instructor> findAllByAtivoTrue();
}