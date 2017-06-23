package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.CategoriaConta;
import br.edu.utfpr.gabriel.financeiro.modelo.CategoriaMovimentacao;

/**
 * Created by gabriel on 23/06/17.
 */

public class CategoriaContaDAO extends DAO<CategoriaConta> {

    CategoriaContaDAO(Context context){
        super(context);
        tabela = "categoria_conta";
    }
    @Override
    protected CategoriaConta mountObject(Cursor cursor) {
        CategoriaConta categoria = new CategoriaConta();

        categoria.setId(cursor.getInt(cursor.getColumnIndex("id")));
        categoria.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

        return categoria;
    }

    @Override
    public CategoriaConta insert(CategoriaConta objeto) {

        execute(context.getString(R.string.sql_insert_categoria_conta),
                objeto.getDescricao()
        );



        return null;
    }

    @Override
    public CategoriaConta update(CategoriaConta objeto, int id) {
        execute(context.getString(R.string.sql_update_categoria_conta),
                objeto.getDescricao(),
                id
                );


        return null;
    }
}
