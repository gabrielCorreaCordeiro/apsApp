package br.edu.utfpr.gabriel.financeiro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


import br.edu.utfpr.gabriel.financeiro.DAO.BancoDAO;
import br.edu.utfpr.gabriel.financeiro.DAO.CategoriaContaDAO;
import br.edu.utfpr.gabriel.financeiro.DAO.ContasDAO;
import br.edu.utfpr.gabriel.financeiro.modelo.Banco;
import br.edu.utfpr.gabriel.financeiro.modelo.CategoriaConta;
import br.edu.utfpr.gabriel.financeiro.modelo.Contas;

/**
 * Created by tuchinski on 27/06/17.
 */

public class AdicionarContaActivity  extends AppCompatActivity implements View.OnClickListener{

    private CategoriaContaDAO daoCategoriaConta;
    //private ContasDAO daoContas;
    private BancoDAO daoBanco1;
    private Spinner spinnerCategoriaConta;
    private Spinner spinnerBancoConta;
    private EditText editTextSaldoInicial;
    private EditText editTextDescricaoConta;
    private Button buttonSalvarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);

        //daoContas = new ContasDAO(this);
        daoCategoriaConta = new CategoriaContaDAO(this);
        daoBanco1 = new BancoDAO(this);

        buildBancoContaSpinner();
        buildCategoriaContaSpinner();
        buildDescricaoConta();
        buildSaldoInicial();
        buildSalvarConta();
    }

    private void buildCategoriaContaSpinner(){
        List<CategoriaConta> list = daoCategoriaConta.findAll("id", "ASC");
        ArrayAdapter<CategoriaConta> adapter = new ArrayAdapter<CategoriaConta>(this, android.R.layout.simple_list_item_1, list);

        spinnerCategoriaConta = (Spinner) findViewById(R.id.adicionar_categoria_conta);
        spinnerCategoriaConta.setAdapter(adapter);
    }

    private void buildBancoContaSpinner(){
        List<Banco> list = daoBanco1.findAll("id", "ASC");
        ArrayAdapter<Banco> adapter = new ArrayAdapter<Banco>(this, android.R.layout.simple_list_item_1, list);

        spinnerBancoConta = (Spinner) findViewById(R.id.adicionar_nome_banco);
        spinnerBancoConta.setAdapter(adapter);
    }

    private void buildSaldoInicial(){
        editTextSaldoInicial = (EditText)findViewById(R.id.adicionar_saldo_inicial);
    }

    private void buildDescricaoConta(){
        editTextDescricaoConta = (EditText) findViewById(R.id.adicionar_descricao_conta);
    }

    private void buildSalvarConta(){
        buttonSalvarConta = (Button)findViewById(R.id.adicionar_salvar_conta);
        buttonSalvarConta.setOnClickListener(this);
    }
    private CategoriaConta getCategoriaConta(){
        int pos = spinnerCategoriaConta.getSelectedItemPosition();
        return (CategoriaConta) spinnerCategoriaConta.getItemAtPosition(pos);
    }
    private Banco getBanco(){
        int pos = spinnerBancoConta.getSelectedItemPosition();
        return (Banco) spinnerBancoConta.getItemAtPosition(pos);
    }

    private Contas getConta(){
        if(editTextDescricaoConta.getText().toString().isEmpty() || editTextSaldoInicial.getText().toString().isEmpty()){
            return null;
        }

        Contas c = new Contas();
        c.setBanco(getBanco());
        c.setCategoriaConta(getCategoriaConta());
        c.setDescricao(editTextDescricaoConta.getText().toString());
        c.setSaldo(Integer.parseInt(editTextSaldoInicial.getText().toString()));

        return c;
    }

    @Override
    public void onClick(View v) {
        if(getConta() == null){
            finish();
            return;
        }

        ContasDAO contasDAO = new ContasDAO(this);
        contasDAO.insert(getConta());
        finish();
    }
}
