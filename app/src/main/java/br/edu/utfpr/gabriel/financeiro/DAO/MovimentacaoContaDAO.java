package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.Gasto;
import br.edu.utfpr.gabriel.financeiro.util.FormatAll;

/**
 * Created by gabriel on 27/11/16
 */

public class MovimentacaoContaDAO extends DAO<Gasto> {

    public MovimentacaoContaDAO(Context context) {
        super(context);
        tabela = "movimentacao_conta";
    }

    @Override

    // monta um objeto do tipo Gasto
    protected Gasto mountObject(Cursor cursor) {
        Gasto gasto = new Gasto();
        ContasDAO tipoDAO = new ContasDAO(context);
        CategoriaMovimentacaoDAO formaDAO = new CategoriaMovimentacaoDAO(context);

        gasto.setId(cursor.getInt(cursor.getColumnIndex("id")));
        gasto.setData(FormatAll.formatStringForDate(cursor.getString(cursor.getColumnIndex("data")),"yyyy-MM-dd"));
        gasto.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));

        gasto.setConta(tipoDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_tipo"))));
        gasto.setCategoriaMovimentacao(formaDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_forma"))));
        gasto.setDescricaoGasto((cursor.getString(cursor.getColumnIndex("local"))));

        return gasto;
    }

    @Override
    // faz um insert no banco com um objeto Gasto
    public Gasto insert(Gasto gasto) {
        execute(
                context.getString(R.string.sql_insert_movientacao_conta),
                gasto.getValor(),
                FormatAll.formatData(gasto.getData(), "yyyy-MM-dd"),
                gasto.getCategoriaMovimentacao().getId(),
                gasto.getConta().getId(),
                gasto.getDescricaoGasto()
        );

        return null;
    }

    @Override
    // faz um update do banco com um objeto Gasto
    public Gasto update(Gasto gasto, int id) {
        execute(
                context.getString(R.string.sql_update_movimentacao_conta),
                gasto.getValor(),
                FormatAll.formatData(gasto.getData(), "yyyy-MM-dd"),
                gasto.getCategoriaMovimentacao().getId(),
                gasto.getConta().getId(),
                gasto.getDescricaoGasto(),
                id
        );

        return null;
    }
}
