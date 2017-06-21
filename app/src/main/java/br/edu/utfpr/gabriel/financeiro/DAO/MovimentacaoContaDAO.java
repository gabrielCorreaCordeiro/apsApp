package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;

import br.edu.utfpr.gabriel.financeiro.R;
import br.edu.utfpr.gabriel.financeiro.modelo.MovimentacaoConta;
import br.edu.utfpr.gabriel.financeiro.util.FormatAll;

/**
 * Created by gabriel on 27/11/16
 */

public class MovimentacaoContaDAO extends DAO<MovimentacaoConta> {

    public MovimentacaoContaDAO(Context context) {
        super(context);
        tabela = "movimentacao_conta";
    }

    @Override

    // monta um objeto do tipo MovimentacaoConta
    protected MovimentacaoConta mountObject(Cursor cursor) {
        MovimentacaoConta movimentacaoConta = new MovimentacaoConta();
        ContasDAO tipoDAO = new ContasDAO(context);
        CategoriaMovimentacaoDAO formaDAO = new CategoriaMovimentacaoDAO(context);

        movimentacaoConta.setId(cursor.getInt(cursor.getColumnIndex("id")));
        movimentacaoConta.setData(FormatAll.formatStringForDate(cursor.getString(cursor.getColumnIndex("data")),"yyyy-MM-dd"));
        movimentacaoConta.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));

        movimentacaoConta.setConta(tipoDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_tipo"))));
        movimentacaoConta.setCategoriaMovimentacao(formaDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_forma"))));
        movimentacaoConta.setDescricaoGasto((cursor.getString(cursor.getColumnIndex("local"))));

        return movimentacaoConta;
    }

    @Override
    // faz um insert no banco com um objeto MovimentacaoConta
    public MovimentacaoConta insert(MovimentacaoConta movimentacaoConta) {
        execute(
                context.getString(R.string.sql_insert_movientacao_conta),
                movimentacaoConta.getValor(),
                FormatAll.formatData(movimentacaoConta.getData(), "yyyy-MM-dd"),
                movimentacaoConta.getCategoriaMovimentacao().getId(),
                movimentacaoConta.getConta().getId(),
                movimentacaoConta.getDescricaoGasto()
        );

        return null;
    }

    @Override
    // faz um update do banco com um objeto MovimentacaoConta
    public MovimentacaoConta update(MovimentacaoConta movimentacaoConta, int id) {
        execute(
                context.getString(R.string.sql_update_movimentacao_conta),
                movimentacaoConta.getValor(),
                FormatAll.formatData(movimentacaoConta.getData(), "yyyy-MM-dd"),
                movimentacaoConta.getCategoriaMovimentacao().getId(),
                movimentacaoConta.getConta().getId(),
                movimentacaoConta.getDescricaoGasto(),
                id
        );

        return null;
    }
}
