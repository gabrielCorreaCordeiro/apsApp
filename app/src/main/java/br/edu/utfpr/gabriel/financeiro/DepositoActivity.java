package br.edu.utfpr.gabriel.financeiro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.edu.utfpr.gabriel.financeiro.DAO.ContasDAO;
import br.edu.utfpr.gabriel.financeiro.modelo.Contas;

/**
 * Created by tuchinski on 28/06/17.
 */

public class DepositoActivity extends AppCompatActivity implements View.OnClickListener {

    ContasDAO dao;
    Spinner spinnerConta;
    EditText editTextValor;
    Button buttonConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        dao = new ContasDAO(this);
        buildspinnerConta();
        buildValor();
        buildConfirmar();
    }

    private void buildspinnerConta(){
        List<Contas> c = dao.findAll("descricao", "ASC");
        ArrayAdapter<Contas> adapter= new ArrayAdapter<Contas>(this, android.R.layout.simple_list_item_1, c);
        spinnerConta = (Spinner) findViewById(R.id.deposito_conta);
        spinnerConta.setAdapter(adapter);
    }

    private void buildValor(){
        editTextValor = (EditText) findViewById(R.id.deposito_valor);
    }

    private void buildConfirmar(){
        buttonConfirmar = (Button) findViewById(R.id.deposito_adicionar);
        buttonConfirmar.setOnClickListener(this);
    }

    public Contas getContaSaldoAtualizado(){
        if(editTextValor.getText().toString().length() == 0){
            return null;
        }

        int pos = spinnerConta.getSelectedItemPosition();
        Contas c = (Contas) spinnerConta.getItemAtPosition(pos);
        int saldo = c.getSaldo();
        int valorDeposito = Integer.parseInt(editTextValor.getText().toString());
        Toast.makeText(getApplicationContext(), "Valor deposito: " + valorDeposito, Toast.LENGTH_SHORT).show();

        c.setSaldo(saldo+valorDeposito);

        //saldo = saldo + Integer.parseInt(editTextValor.getText().toString());
        //c.setSaldo(c.getSaldo()+Integer.parseInt(editTextValor.getText().toString()));

        return c;
    }



    public void onClick(View view){
        if(getContaSaldoAtualizado() == null){
            finish();
            return;
        }
        ContasDAO dao = new ContasDAO(this);
        Contas c = getContaSaldoAtualizado();
        dao.update(c, c.getId());
        Toast.makeText(getApplicationContext(), "O novo salde é de: " + c.getSaldo(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
