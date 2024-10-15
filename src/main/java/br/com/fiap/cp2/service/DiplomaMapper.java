package br.com.fiap.cp2.service;

import br.com.fiap.cp2.dto.DiplomaRequest;
import br.com.fiap.cp2.dto.DiplomaResponse;
import br.com.fiap.cp2.model.Curso;
import br.com.fiap.cp2.model.Diploma;
import br.com.fiap.cp2.model.Diplomado;
import br.com.fiap.cp2.repository.CursoRepository;
import br.com.fiap.cp2.repository.DiplomadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiplomaMapper {
    @Autowired
    private DiplomadoMapper diplomadoMapper;
    @Autowired
    private  CursoMapper cursoMapper;
    @Autowired
    private DiplomadoRepository diplomadoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Diploma requestToDiploma(DiplomaRequest dto) {
        Diploma diploma = new Diploma();
        Diplomado diplomado = diplomadoRepository.findById(dto.diplomadoId())
                .orElseThrow(() -> new RuntimeException("Diplomado não encontrado"));
        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        diploma.setDiplomado(diplomado);
        diploma.setCurso(curso);
        diploma.setDataConclusao(dto.dataConclusao());
        diploma.setSexoReitor(dto.sexoReitor());
        diploma.setNomeReitor(dto.nomeReitor());
        return diploma;
    }

    public DiplomaResponse diplomaToResponse(Diploma diploma) {
        return new DiplomaResponse(
                diploma.getId(),
                diplomadoMapper.diplomadoToResponse(diploma.getDiplomado()),
                cursoMapper.cursoToResponse(diploma.getCurso()),
                diploma.getDataConclusao(),
                diploma.getSexoReitor().name(),
                diploma.getNomeReitor()
        );
    }
}
