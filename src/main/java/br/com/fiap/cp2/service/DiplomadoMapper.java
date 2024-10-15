package br.com.fiap.cp2.service;

import br.com.fiap.cp2.dto.DiplomadoRequest;
import br.com.fiap.cp2.dto.DiplomadoResponse;
import br.com.fiap.cp2.model.Diplomado;
import org.springframework.stereotype.Service;

@Service
public class DiplomadoMapper {

    public Diplomado requestToDiplomado(DiplomadoRequest dto) {
        Diplomado diplomado = new Diplomado();
        diplomado.setNome(dto.nome());
        diplomado.setNacionalidade(dto.nacionalidade());
        diplomado.setNaturalidade(dto.naturalidade());
        diplomado.setRg(dto.rg());
        return diplomado;
    }

    public DiplomadoResponse diplomadoToResponse(Diplomado diplomado) {
        return new DiplomadoResponse(
                diplomado.getId(),
                diplomado.getNome(),
                diplomado.getNacionalidade(),
                diplomado.getNaturalidade(),
                diplomado.getRg()
        );
    }
}
