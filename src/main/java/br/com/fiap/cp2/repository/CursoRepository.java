package br.com.fiap.cp2.repository;

import br.com.fiap.cp2.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
