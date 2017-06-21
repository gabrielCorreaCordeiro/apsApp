package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.CategoriaMovimentacao;

/**
 * Created by gabriel on 30/11/16
 */

public class FormaDePagamentoDAO extends DAO<CategoriaMovimentacao> {
    public FormaDePagamentoDAO(Context context) {
        super(context);
        tabela = "categoria_movimentacao";
    }

    @Override
    // monta um objeto do tipo formaDePagamento
    protected CategoriaMovimentacao mountObject(Cursor cursor) {
        CategoriaMovimentacao categoriaMovimentacao = new CategoriaMovimentacao();

        categoriaMovimentacao.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        categoriaMovimentacao.setId(cursor.getInt(cursor.getColumnIndex("id")));

        return categoriaMovimentacao;
    }

    @Override
    // faz um insert de um objeto formaDePagamento
    public CategoriaMovimentacao insert(CategoriaMovimentacao objeto) {
        execute(
                context.getString(R.string.sql_insert_categoria_movimentacao),
                objeto.getDescricao()
        );
        return null;
    }

    @Override

    // faz um update no banco do objeto formaDePagamento
    public CategoriaMovimentacao update(CategoriaMovimentacao objeto, int id) {
        execute(
                context.getString(R.string.sql_update_ategoria_movimentacao),
                objeto.getDescricao(),
                id
        );
        return null;
    }
}
