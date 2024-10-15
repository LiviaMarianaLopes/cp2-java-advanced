package br.com.fiap.cp2.service;

import br.com.fiap.cp2.dto.CursoRequest;
import br.com.fiap.cp2.dto.CursoResponse;
import br.com.fiap.cp2.model.Curso;
import org.springframework.stereotype.Service;

@Service
public class CursoMapper {

    public Curso requestToCurso(CursoRequest dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setTipo(dto.tipo());
        return curso;
    }

    public CursoResponse cursoToResponse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getTipo().name()
        );
    }
}
