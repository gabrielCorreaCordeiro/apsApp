package br.edu.utfpr.gabriel.financeiro.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.gabriel.financeiro.R;

/**
 * Created by gabriel on 24/11/16
 */

public abstract class DAO<T> extends SQLiteOpenHelper {

    protected Context context;
    protected String tabela;


    public DAO(Context context) {
        super(context, "dataBase", null, 1);
        this.context = context;
    }
    ///create das tabelas referenciadas no sql.xml
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE_FORMA_PAGAMENTO = context.getString(R.string.sql_create_table_categoria_movimentacao);
        db.execSQL(SQL_CREATE_TABLE_FORMA_PAGAMENTO);

        final String SQL_CREATE_TABLE_TIPO = context.getString(R.string.sql_create_table_conta);
        db.execSQL(SQL_CREATE_TABLE_TIPO);


        final String SQL_CREATE_TABLE_GASTO = context.getString(R.string.sql_create_table_movimentacao_conta);
        db.execSQL(SQL_CREATE_TABLE_GASTO);

        final String SQL_INSERT_DEFAULT_TIPO = context.getString(R.string.sql_insert_contas);
        db.execSQL(SQL_INSERT_DEFAULT_TIPO,new String[]{"BANCO DO BRASIL"});
        db.execSQL(SQL_INSERT_DEFAULT_TIPO,new String[]{"CAIXA"});
        db.execSQL(SQL_INSERT_DEFAULT_TIPO,new String[]{"BRADESCO"});


        final String SQL_INSERT_DEFAULT_FORMA = context.getString(R.string.sql_insert_categoria_movimentacao);
        db.execSQL(SQL_INSERT_DEFAULT_FORMA,new String[]{"Cartão de Crédito"});
        db.execSQL(SQL_INSERT_DEFAULT_FORMA,new String[]{"Cartão de Débito"});
        db.execSQL(SQL_INSERT_DEFAULT_FORMA,new String[]{"Dinheiro"});
        db.execSQL(SQL_INSERT_DEFAULT_FORMA,new String[]{"Cheque"});
        db.execSQL(SQL_INSERT_DEFAULT_FORMA,new String[]{"Boleto Bancário"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    ///traz a tabela completa
    public List<T> findAll (String field, String order) {
        String sql = String.format("SELECT * FROM %s ORDER BY %s %s", tabela, field, order);
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        List<T> list = new ArrayList<>();

        while (cursor.moveToNext()){
            list.add(mountObject(cursor));
        }
        cursor.close();
        return list;
    }

    public T findOne(int id){
        String sql = String.format("SELECT * FROM %s WHERE id = ? LIMIT 1", tabela);
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(id)});

        T objeto = null;
        if (cursor.moveToFirst()){
            objeto = mountObject(cursor);

        }
        cursor.close();
        return objeto;
    }

    public void execute(String sql, Object... args) {
        getWritableDatabase().execSQL(sql, args);
    }

    protected abstract T mountObject(Cursor cursor);
    //protected abstract void insertTobanck(T objeto);

    public abstract T insert(T objeto);
    public abstract T update(T objeto, int id);
}
