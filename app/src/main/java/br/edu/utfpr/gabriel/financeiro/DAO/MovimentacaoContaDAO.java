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
        ContasDAO contasDAO = new ContasDAO(context);
        CategoriaMovimentacaoDAO categoriaMovimentacaoDAO = new CategoriaMovimentacaoDAO(context);
        TipoMovimentacaoDAO tipoMovimentacaoDAO = new TipoMovimentacaoDAO(context);

        movimentacaoConta.setId(cursor.getInt(cursor.getColumnIndex("id")));
        movimentacaoConta.setData(FormatAll.formatStringForDate(cursor.getString(cursor.getColumnIndex("data")),"yyyy-MM-dd"));
        movimentacaoConta.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));

        movimentacaoConta.setConta(contasDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_conta"))));
        movimentacaoConta.setCategoriaMovimentacao(categoriaMovimentacaoDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_categoria_movi"))));
        movimentacaoConta.setTipoMovimentacao(tipoMovimentacaoDAO.findOne(cursor.getInt(cursor.getColumnIndex("id_tipo_movi"))));


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
                movimentacaoConta.getDescricao()
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
                movimentacaoConta.getDescricao(),
                id
        );

        return null;
    }
}
