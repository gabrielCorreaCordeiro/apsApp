package br.edu.utfpr.gabriel.financeiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import br.edu.utfpr.gabriel.financeiro.DAO.GastoDAO;
import br.edu.utfpr.gabriel.financeiro.adapter.GastoAdapter;
import br.edu.utfpr.gabriel.financeiro.modelo.Gasto;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

        private RecyclerView listGasto;
        private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        /// cria o toolbar
        buildToolbar();

        /// Botão de adicionar novo gasto
        buildbutton();
        /// Drawer lateral
        buildDrawer();

        ///lista da pagina inicial
        buildListaGasto();

        preencherListaGasto();

    }

    @Override
    protected void onResume() { // preenche com todos os gastos as telas
        super.onResume();
        buildListaGasto();
        preencherListaGasto();
    }

    private void buildToolbar () {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void buildDrawer (){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void buildbutton (){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(this);

    }

    private void buildListaGasto (){
        listGasto = (RecyclerView)findViewById(R.id.list_gastos);

        StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        listGasto.setLayoutManager(llm);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void preencherListaGasto(){
        GastoDAO gastoDAO = new GastoDAO(this);
        GastoAdapter adapter = new GastoAdapter(this);
        adapter.setItems(gastoDAO.findAll("data","DESC"));
        listGasto.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,GastoActivity.class);
        startActivity(intent);

    }
}
