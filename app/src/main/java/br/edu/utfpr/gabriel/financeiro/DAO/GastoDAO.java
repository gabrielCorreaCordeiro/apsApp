package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.Gasto;
import br.edu.utfpr.gabriel.financeiro.util.FormatAll;

/**
 * Created by gabriel on 27/11/16
 */

public class GastoDAO extends DAO<Gasto> {

    public GastoDAO(Context context) {
        super(context);
        tabela = "gasto";
    }

    @Override

    // monta um objeto do tipo Gasto
    protected Gasto mountObject(Cursor cursor) {
        Gasto gasto = new Gasto();
        ContasDAO tipoDAO = new ContasDAO(context);
        FormaDePagamentoDAO formaDAO = new FormaDePagamentoDAO(context);

        gasto.setId(cursor.getInt(cursor.getColumnIndex("id")));
        gasto.setData(FormatAll.formatStringForDate(cursor.getString(cursor.getColumnIndex("data")),"yyyy-MM-dd"));
        gasto.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));

        gasto.setTipo(tipoDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_tipo"))));
        gasto.setFormaDePagamento(formaDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_forma"))));
        gasto.setDescricaoGasto((cursor.getString(cursor.getColumnIndex("local"))));

        return gasto;
    }

    @Override
    // faz um insert no banco com um objeto Gasto
    public Gasto insert(Gasto gasto) {
        execute(
                context.getString(R.string.sql_insert_gasto),
                gasto.getValor(),
                FormatAll.formatData(gasto.getData(), "yyyy-MM-dd"),
                gasto.getFormaDePagamento().getId(),
                gasto.getTipo().getId(),
                gasto.getDescricaoGasto()
        );

        return null;
    }

    @Override
    // faz um update do banco com um objeto Gasto
    public Gasto update(Gasto gasto, int id) {
        execute(
                context.getString(R.string.sql_update_gasto),
                gasto.getValor(),
                FormatAll.formatData(gasto.getData(), "yyyy-MM-dd"),
                gasto.getFormaDePagamento().getId(),
                gasto.getTipo().getId(),
                gasto.getDescricaoGasto(),
                id
        );

        return null;
    }
}
