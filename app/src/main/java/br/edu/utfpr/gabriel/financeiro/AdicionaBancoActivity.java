package br.edu.utfpr.gabriel.financeiro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.utfpr.gabriel.financeiro.DAO.BancoDAO;
import br.edu.utfpr.gabriel.financeiro.modelo.Banco;

public class AdicionaBancoActivity extends AppCompatActivity implements View.OnClickListener {

    //private BancoDAO daoBanco;
    private EditText adicionaBanco_Nome;
    private Button adicionaBanco_Botao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_banco);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adicionaBanco_Nome = (EditText) findViewById(R.id.adiciona_banco_nome);
        adicionaBanco_Botao = (Button) findViewById(R.id.adiciona_banco_salvar);
        adicionaBanco_Botao.setOnClickListener(this);



    }

    public Banco getBanco(){
        Banco b = new Banco();
        b.setNomeBanco(adicionaBanco_Nome.getText().toString());
        return b;
    }

    public void onClick(View view){
        BancoDAO bancoDAO = new BancoDAO(this);
        bancoDAO.insert(getBanco());
        finish();
    }



}
