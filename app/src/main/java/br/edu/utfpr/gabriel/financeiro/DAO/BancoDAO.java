package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.Banco;


/**
 * Created by gabriel on 23/06/17.
 */

public class BancoDAO extends DAO<Banco>  {

    BancoDAO(Context context){
        super(context);
        tabela = "banco";
    }

    @Override
    protected Banco mountObject(Cursor cursor) {
        Banco banco = new Banco();

        banco.setId(cursor.getInt(cursor.getColumnIndex("id")));
        banco.setNomeBanco(cursor.getString(cursor.getColumnIndex("nomeBanco")));

        return banco;
    }

    @Override
    public Banco insert(Banco objeto) {

        execute(context.getString(R.string.sql_insert_banco),
                objeto.getNomeBanco());

        return null;
    }

    @Override
    public Banco update(Banco objeto, int id) {

        execute(context.getString(R.string.sql_update_banco),
                objeto.getNomeBanco(),
                id);

        return null;
    }
}
