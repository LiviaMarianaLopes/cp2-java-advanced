package br.com.fiap.cp2.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_diplomado",
        referencedColumnName = "id"
    )
    private Diplomado diplomado;
    @ManyToOne
    @JoinColumn(name = "id_curso",
            referencedColumnName = "id"
    )    private Curso curso;
    private LocalDate dataConclusao;
    @Enumerated(EnumType.STRING)
    private Sexo sexoReitor;
    private String nomeReitor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Diplomado getDiplomado() {
        return diplomado;
    }

    public void setDiplomado(Diplomado diplomado) {
        this.diplomado = diplomado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Sexo getSexoReitor() {
        return sexoReitor;
    }

    public void setSexoReitor(Sexo sexoReitor) {
        this.sexoReitor = sexoReitor;
    }

    public String getNomeReitor() {
        return nomeReitor;
    }

    public void setNomeReitor(String nomeReitor) {
        this.nomeReitor = nomeReitor;
    }
}

