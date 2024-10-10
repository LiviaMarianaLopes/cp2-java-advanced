package br.com.fiap.cp2.model;

public enum TipoCurso {
    GRADUACAO("Graduação"),
    POS_GRADUACAO("Pós graduação");
    private String descricao;
    TipoCurso(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
