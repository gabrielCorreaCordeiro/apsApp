package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.TipoMovimentacao;

/**
 * Created by gabriel on 23/06/17.
 */

public class TipoMovimentacaoDAO extends DAO<TipoMovimentacao> {

    TipoMovimentacaoDAO (Context context){
        super(context);
        tabela = "tipo_movimentacao";


    }
    @Override
    protected TipoMovimentacao mountObject(Cursor cursor) {
        TipoMovimentacao tipoMovimentacao = new TipoMovimentacao();

        tipoMovimentacao.setId(cursor.getInt(cursor.getColumnIndex("id")));
        tipoMovimentacao.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

        return tipoMovimentacao;
    }

    @Override
    public TipoMovimentacao insert(TipoMovimentacao objeto) {
        execute(context.getString(R.string.sql_insert_tipo_movimentacao),
                objeto.getDescricao()
                );
        return null;
    }

    @Override
    public TipoMovimentacao update(TipoMovimentacao objeto, int id) {
        execute(context.getString(R.string.sql_update_tipo_movimentacao),
                objeto.getDescricao(),
                id
                );
        return null;
    }
}
