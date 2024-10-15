package br.com.fiap.cp2.controller;

import br.com.fiap.cp2.dto.CursoRequest;
import br.com.fiap.cp2.dto.CursoResponse;
import br.com.fiap.cp2.model.Curso;
import br.com.fiap.cp2.repository.CursoRepository;
import br.com.fiap.cp2.service.CursoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @PostMapping
    public ResponseEntity<CursoResponse> createCurso(@Valid @RequestBody CursoRequest cursoRequest) {
        Curso curso = cursoMapper.requestToCurso(cursoRequest);
        Curso savedCurso = cursoRepository.save(curso);
        CursoResponse cursoResponse = cursoMapper.cursoToResponse(savedCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoResponse);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponse>> readCursos() {
        List<Curso> listaCursos = cursoRepository.findAll();
        if (listaCursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CursoResponse> listaCursosResponse = new ArrayList<>();
        for (Curso curso : listaCursos) {
            CursoResponse cursoResponse = cursoMapper.cursoToResponse(curso);
            listaCursosResponse.add(cursoResponse);
        }
        return new ResponseEntity<>(listaCursosResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> getCursoById(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CursoResponse cursoResponse = cursoMapper.cursoToResponse(curso.get());
        return ResponseEntity.ok(cursoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> updateCurso(@PathVariable Long id, @Valid @RequestBody CursoRequest cursoRequest) {
        Optional<Curso> cursoSalvo = cursoRepository.findById(id);
        if (cursoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Curso curso = cursoMapper.requestToCurso(cursoRequest);
        curso.setId(id);
        Curso updatedCurso = cursoRepository.save(curso);
        CursoResponse cursoResponse = cursoMapper.cursoToResponse(updatedCurso);
        return ResponseEntity.ok(cursoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

