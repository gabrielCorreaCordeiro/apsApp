package br.edu.utfpr.gabriel.financeiro;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.edu.utfpr.gabriel.financeiro.DAO.CategoriaMovimentacaoDAO;
import br.edu.utfpr.gabriel.financeiro.DAO.MovimentacaoContaDAO;
import br.edu.utfpr.gabriel.financeiro.DAO.ContasDAO;
import br.edu.utfpr.gabriel.financeiro.modelo.CategoriaMovimentacao;
import br.edu.utfpr.gabriel.financeiro.modelo.MovimentacaoConta;
import br.edu.utfpr.gabriel.financeiro.modelo.Contas;
import br.edu.utfpr.gabriel.financeiro.util.FormatAll;

public class GastoActivity extends AppCompatActivity implements View.OnClickListener {


    private CategoriaMovimentacaoDAO daoForma;
    private ContasDAO dao;
    private Spinner mGastoSpinner;
    private EditText data;
    private EditText local;
    private Spinner forma;
    private Button salvar;
    private EditText valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto);

        dao = new ContasDAO(this);
        daoForma = new CategoriaMovimentacaoDAO(this);
        buildGastoSpinner();
        buildFormaDePagamento();
        buildButton();
        buildValor();
        buildLocal();
        buildData();
    }

    private void buildGastoSpinner() {
        List<Contas> list = dao.findAll("descricao", "ASC");
        ArrayAdapter<Contas> adapter = new ArrayAdapter<Contas>(this, android.R.layout.simple_list_item_1, list);

        mGastoSpinner = (Spinner) findViewById(R.id.spinner_tipo);
        mGastoSpinner.setAdapter(adapter);
    }
    private  void buildFormaDePagamento() {
        List<CategoriaMovimentacao> list = daoForma.findAll("descricao","ASC");
        ArrayAdapter<CategoriaMovimentacao> adapter = new ArrayAdapter<CategoriaMovimentacao>(this,android.R.layout.simple_list_item_1,list);

        forma = (Spinner) findViewById(R.id.spinner_forma);
        forma.setAdapter(adapter);
    }

    private void buildValor(){
        valor = (EditText) findViewById(R.id.text_entrada_valor_gasto);

    }
    private void buildLocal(){
        local = (EditText) findViewById(R.id.text_Entrada_descricao_gasto);
    }

    private void buildButton(){
        salvar = (Button)  findViewById(R.id.salvar);
        salvar.setOnClickListener(this);

    }

    private void buildData(){
        data = (EditText) findViewById(R.id.text_entrada_data);
    }

    private CategoriaMovimentacao getFormaPagamento (){
        int pos = forma.getSelectedItemPosition();
        return (CategoriaMovimentacao) forma.getItemAtPosition(pos);
    }

    private Contas getConta(){
        int pos = mGastoSpinner.getSelectedItemPosition();
        return (Contas) mGastoSpinner.getItemAtPosition(pos);
    }



    private MovimentacaoConta getMovimentacaoConta(){

        if(valor.getText().toString().isEmpty()|| data.getText().toString().isEmpty() || valor.getText().toString().isEmpty()) {
            return null;

        }
        MovimentacaoConta movimentacaoConta = new MovimentacaoConta();

        movimentacaoConta.setDescricao(local.getText().toString());
        movimentacaoConta.setValor(Float.parseFloat(valor.getText().toString()));
        movimentacaoConta.setCategoriaMovimentacao(getFormaPagamento());
        movimentacaoConta.setConta(getConta());
        movimentacaoConta.setData(FormatAll.formatStringForDate(data.getText().toString(),"dd/MM/yyyy"));




        return movimentacaoConta;

    }



    @Override
    public void onClick(View view) {
        if(getMovimentacaoConta() == null ) {
            finishActivity(0);
        }
        MovimentacaoContaDAO movimentacaoContaDAO = new MovimentacaoContaDAO(this);
        movimentacaoContaDAO.insert(getMovimentacaoConta());
        finish();

    }



}
