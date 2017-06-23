package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.Banco;
import br.edu.utfpr.gabriel.financeiro.modelo.Contas;

/**
 * Created by gabriel on 30/11/16
 */

public class ContasDAO extends DAO<Contas> {

    public ContasDAO(Context context) {
        super(context);
        tabela = "conta";
    }

    @Override

    //monta um objeto do tipo contas
    protected Contas mountObject(Cursor cursor) {
        Contas contas = new Contas();
        CategoriaContaDAO categoriaConta = new CategoriaContaDAO(context);
        BancoDAO banCoDAO = new BancoDAO(context);

        contas.setId(cursor.getInt(cursor.getColumnIndex("id")));
        contas.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        contas.setSaldo(cursor.getInt(cursor.getColumnIndex("saldo")));
        contas.setCategoriaConta(categoriaConta.findOne(cursor.getInt(cursor.getColumnIndex("id_categoria"))));
        contas.setBanco(banCoDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_banco"))));


        return contas;
    }

    @Override

    // faz um insert de uma contas no banco
    public Contas insert(Contas objeto) {
        execute(
                context.getString(R.string.sql_insert_contas),
                objeto.getDescricao(),
                objeto.getSaldo(),
                objeto.getCategoriaConta().getId(),
                objeto.getBanco().getId()
        );

        return null;
    }

    @Override

    //gera um update no banco do objeto conta
    public Contas update(Contas objeto, int id) {
        execute(
                context.getString(R.string.sql_update_contas),
                objeto.getDescricao(),
                objeto.getSaldo(),
                objeto.getCategoriaConta().getId(),
                objeto.getBanco().getId(),
                id
        );
        return null;
    }
}
