package br.edu.utfpr.gabriel.financeiro.modelo;

/**
 * Created by gabriel on 27/11/16
 */

public class Contas {
    private int id;
    private String descricao;
    private int saldo;
    private Banco banco;
    private CategoriaConta categoriaConta;

    public Contas() {
    }

    public Contas(int id, String descricao) {
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

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public CategoriaConta getCategoriaConta() {
        return categoriaConta;
    }

    public void setCategoriaConta(CategoriaConta categoriaConta) {
        this.categoriaConta = categoriaConta;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
