package br.edu.utfpr.gabriel.financeiro.modelo;

/**
 * Created by gabriel on 27/11/16
 */

public class CategoriaMovimentacao {

    private int id;
    private String descricao;

    public CategoriaMovimentacao() {
    }

    public CategoriaMovimentacao(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
