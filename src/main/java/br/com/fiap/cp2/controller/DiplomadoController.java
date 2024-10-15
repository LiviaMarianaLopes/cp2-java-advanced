package br.com.fiap.cp2.controller;

import br.com.fiap.cp2.dto.DiplomadoRequest;
import br.com.fiap.cp2.dto.DiplomadoResponse;
import br.com.fiap.cp2.model.Diplomado;
import br.com.fiap.cp2.repository.DiplomadoRepository;
import br.com.fiap.cp2.service.DiplomadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diplomados")
public class DiplomadoController {
    @Autowired
    private DiplomadoRepository diplomadoRepository;
    @Autowired
    private DiplomadoMapper diplomadoMapper;

    @PostMapping
    public ResponseEntity<DiplomadoResponse> createDiplomado(@Valid @RequestBody DiplomadoRequest diplomadoRequest) {
        Diplomado diplomado = diplomadoMapper.requestToDiplomado(diplomadoRequest);
        Diplomado savedDiplomado = diplomadoRepository.save(diplomado);
        DiplomadoResponse diplomadoResponse = diplomadoMapper.diplomadoToResponse(savedDiplomado);
        return ResponseEntity.status(HttpStatus.CREATED).body(diplomadoResponse);
    }

    @GetMapping
    public ResponseEntity<List<DiplomadoResponse>> readDiplomados() {
        List<Diplomado> listaDiplomados = diplomadoRepository.findAll();
        if (listaDiplomados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DiplomadoResponse> listaDiplomadosResponse = new ArrayList<>();
        for (Diplomado diplomado : listaDiplomados) {
            DiplomadoResponse diplomadoResponse = diplomadoMapper.diplomadoToResponse(diplomado);
            listaDiplomadosResponse.add(diplomadoResponse);
        }
        return new ResponseEntity<>(listaDiplomadosResponse, HttpStatus.OK);
    }


    // Read (get by id)
    @GetMapping("/{id}")
    public ResponseEntity<DiplomadoResponse> getDiplomadoById(@PathVariable Long id) {
        Optional<Diplomado> diplomado = diplomadoRepository.findById(id);
        if (diplomado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DiplomadoResponse diplomadoResponse = diplomadoMapper.diplomadoToResponse(diplomado.get());
        return ResponseEntity.ok(diplomadoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiplomadoResponse> updateDiplomado(@PathVariable Long id, @Valid @RequestBody DiplomadoRequest diplomadoRequest) {
        Optional<Diplomado> diplomadoSalvo = diplomadoRepository.findById(id);
        if (diplomadoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Diplomado diplomado = diplomadoMapper.requestToDiplomado(diplomadoRequest);
        diplomado.setId(id);
        Diplomado updatedDiplomado = diplomadoRepository.save(diplomado);
        DiplomadoResponse diplomadoResponse = diplomadoMapper.diplomadoToResponse(updatedDiplomado);
        return ResponseEntity.ok(diplomadoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiplomado(@PathVariable Long id) {
        diplomadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

