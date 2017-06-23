package br.edu.utfpr.gabriel.financeiro.modelo;

import java.util.Date;

/**
 * Created by gabriel on 27/11/16.
 */

public class MovimentacaoConta {
    private int id;
    private float valor;
    private Date data;
    private Contas conta;
    private CategoriaMovimentacao categoriaMovimentacao;
    private String descricao;
    private TipoMovimentacao tipoMovimentacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Contas getConta() {
        return conta;
    }

    public void setConta(Contas tipo) {
        this.conta = tipo;
    }

    public CategoriaMovimentacao getCategoriaMovimentacao() {
        return categoriaMovimentacao;
    }

    public void setCategoriaMovimentacao(CategoriaMovimentacao categoriaMovimentacao) {
        this.categoriaMovimentacao = categoriaMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
