package br.com.fiap.cp2.repository;

import br.com.fiap.cp2.model.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DiplomaRepository extends JpaRepository<Diploma, UUID> {
    List<Diploma> findByDiplomadoRg(String rg);
}
