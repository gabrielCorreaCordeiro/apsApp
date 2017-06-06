package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.FormaDePagamento;

/**
 * Created by gabriel on 30/11/16
 */

public class FormaDePagamentoDAO extends DAO<FormaDePagamento> {
    public FormaDePagamentoDAO(Context context) {
        super(context);
        tabela = "forma_de_pagamento";
    }

    @Override
    // monta um objeto do tipo formaDePagamento
    protected FormaDePagamento mountObject(Cursor cursor) {
        FormaDePagamento formaDePagamento= new FormaDePagamento();

        formaDePagamento.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        formaDePagamento.setId(cursor.getInt(cursor.getColumnIndex("id")));

        return  formaDePagamento;
    }

    @Override
    // faz um insert de um objeto formaDePagamento
    public FormaDePagamento insert(FormaDePagamento objeto) {
        execute(
                context.getString(R.string.sql_insert_forma_pagamento),
                objeto.getDescricao()
        );
        return null;
    }

    @Override

    // faz um update no banco do objeto formaDePagamento
    public FormaDePagamento update(FormaDePagamento objeto, int id) {
        execute(
                context.getString(R.string.sql_update_forma_pagamento),
                objeto.getDescricao(),
                id
        );
        return null;
    }
}
