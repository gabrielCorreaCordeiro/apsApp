package br.edu.utfpr.gabriel.financeiro.modelo;

import java.util.Date;

/**
 * Created by gabriel on 27/11/16.
 */

public class Gasto {
    private int id;
    private float valor;
    private Date data;
    private Contas tipo;
    private FormaDePagamento formaDePagamento;
    private String descricaoGasto;

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

    public Contas getTipo() {
        return tipo;
    }

    public void setTipo(Contas tipo) {
        this.tipo = tipo;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public String getDescricaoGasto() {
        return descricaoGasto;
    }

    public void setDescricaoGasto(String descricaoGasto) {
        this.descricaoGasto = descricaoGasto;
    }
}
