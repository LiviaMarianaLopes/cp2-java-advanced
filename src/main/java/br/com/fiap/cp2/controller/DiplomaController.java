package br.com.fiap.cp2.controller;

import br.com.fiap.cp2.dto.DiplomaRequest;
import br.com.fiap.cp2.dto.DiplomaResponse;
import br.com.fiap.cp2.model.Diploma;
import br.com.fiap.cp2.repository.DiplomaRepository;
import br.com.fiap.cp2.service.DiplomaMapper;
import br.com.fiap.cp2.model.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/diplomas")
public class DiplomaController {
    @Autowired
    private DiplomaRepository diplomaRepository;
    @Autowired
    private DiplomaMapper diplomaMapper;

    @PostMapping
    public ResponseEntity<DiplomaResponse> createDiploma(@Valid @RequestBody DiplomaRequest diplomaRequest) {
        Diploma diploma = diplomaMapper.requestToDiploma(diplomaRequest);
        Diploma savedDiploma = diplomaRepository.save(diploma);
        DiplomaResponse diplomaResponse = diplomaMapper.diplomaToResponse(savedDiploma);
        return ResponseEntity.status(HttpStatus.CREATED).body(diplomaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DiplomaResponse>> readDiplomas() {
        List<Diploma> listaDiplomas = diplomaRepository.findAll();
        if (listaDiplomas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<DiplomaResponse> listaDiplomasResponse = new ArrayList<>();
        for (Diploma diploma : listaDiplomas) {
            DiplomaResponse diplomaResponse = diplomaMapper.diplomaToResponse(diploma);
            listaDiplomasResponse.add(diplomaResponse);
        }
        return new ResponseEntity<>(listaDiplomasResponse, HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DiplomaResponse> updateDiploma(@PathVariable UUID uuid, @Valid @RequestBody DiplomaRequest diplomaRequest) {
        Optional<Diploma> diplomaSalvo = diplomaRepository.findById(uuid);
        if (diplomaSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Diploma diploma = diplomaMapper.requestToDiploma(diplomaRequest);
        diploma.setId(uuid);
        Diploma updatedDiploma = diplomaRepository.save(diploma);
        DiplomaResponse diplomaResponse = diplomaMapper.diplomaToResponse(updatedDiploma);
        return ResponseEntity.ok(diplomaResponse);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteDiploma(@PathVariable UUID uuid) {
        diplomaRepository.deleteById(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<String> getDiplomaByUuid(@PathVariable UUID uuid) {
        Optional<Diploma> diploma = diplomaRepository.findById(uuid);
        if (diploma == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diploma não encontrado.");
        }
        Diploma diplomaEncontrado = diploma.get();
        String textoDiploma = gerarTextoDiploma(diplomaEncontrado);
        return ResponseEntity.ok(textoDiploma);
    }

    private String gerarTextoDiploma(Diploma diploma) {
        String tituloReitor = diploma.getSexoReitor() == Sexo.M ? "O Prof. Dr." : "A Profa. Dra.";
        String cargoReitor = diploma.getSexoReitor() == Sexo.M ? "reitor" : "reitora";
        return String.format(
                "%s %s, %s da Universidade Fake, confere a %s, de nacionalidade %s, natural de %s, portador do rg %s, o diploma de %s, no curso de %s, concluído em %s.",
                tituloReitor, diploma.getNomeReitor(), cargoReitor,
                diploma.getDiplomado().getNome(), diploma.getDiplomado().getNacionalidade(),
                diploma.getDiplomado().getNaturalidade(), diploma.getDiplomado().getRg(),
                diploma.getCurso().getTipo().name().toLowerCase(), diploma.getCurso().getNome(),
                diploma.getDataConclusao()
        );
    }
}

