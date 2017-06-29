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
import br.edu.utfpr.gabriel.financeiro.DAO.MovimentacaoContaDAO;
import br.edu.utfpr.gabriel.financeiro.modelo.Contas;

/**
 * Created by tuchinski on 27/06/17.
 */

public class TransferenciaActivity extends AppCompatActivity implements View.OnClickListener {

    private ContasDAO daoContas;
    private MovimentacaoContaDAO daoMovimentacao;
    private Spinner spinnerContaOrigem;
    private Spinner spinnerContaDestino;
    private EditText editTextData;
    private EditText editTextValor;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto);

        daoContas = new ContasDAO(this);
        daoMovimentacao = new MovimentacaoContaDAO(this);

        buildButton();
        buildContaDestino();
        buildContaOrigem();
        buildData();
        buildValor();
    }

    private void buildContaOrigem(){
        List<Contas> list = daoContas.findAll("descricao", "ASC");
        ArrayAdapter<Contas> adapter = new ArrayAdapter<Contas>(this, android.R.layout.simple_list_item_1, list);

        spinnerContaOrigem = (Spinner) findViewById(R.id.transferencia_conta_origem);
        spinnerContaOrigem.setAdapter(adapter);
    }

    private void buildContaDestino(){
        List<Contas> list = daoContas.findAll("descricao", "ASC");
        ArrayAdapter<Contas> adapter = new ArrayAdapter<Contas>(this, android.R.layout.simple_list_item_1, list);

        spinnerContaDestino = (Spinner) findViewById(R.id.transferencia_conta_destino);
        spinnerContaDestino.setAdapter(adapter);
    }

    private void buildData(){
        editTextData = (EditText) findViewById(R.id.tranferencia_data);
    }
    private void buildValor(){
        editTextValor = (EditText) findViewById(R.id.transferencia_valor);
    }

    private void buildButton(){
        buttonSalvar = (Button) findViewById(R.id.transferencia_salvar);
    }

    private Contas getContaOrigem(){
        int pos = spinnerContaOrigem.getSelectedItemPosition();
        return (Contas) spinnerContaDestino.getItemAtPosition(pos);
    }

    private Contas getContaDestino(){
        int pos = spinnerContaDestino.getSelectedItemPosition();
        return (Contas) spinnerContaDestino.getItemAtPosition(pos);
    }

    private boolean verificaTransferencia(Contas origem, Contas destino){
        //Contas origem = new Contas();
        //Contas destino = new Contas();
        int valorTransferencia = Integer.parseInt(editTextValor.getText().toString());

        if(origem == destino){
            Toast.makeText(getApplicationContext(), "A conta de origem e destino não pode ser a mesma", Toast.LENGTH_SHORT).show();
            finish();
            return false;
        }

        if(origem.getSaldo()< valorTransferencia){
            Toast.makeText(getApplicationContext(), "Saldo indisponível", Toast.LENGTH_SHORT).show();
            return false;
        }else return true;

    }




    @Override
    public void onClick(View view){
        if(!verificaTransferencia(getContaOrigem(), getContaDestino())){
            finish();
            return;
        }

    }
}
