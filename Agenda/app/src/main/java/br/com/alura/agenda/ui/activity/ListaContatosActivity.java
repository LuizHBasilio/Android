package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_CONTATO;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Contato;
import br.com.alura.agenda.ui.ListaContatosView;

public class ListaContatosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Contatos";
    private ListaContatosView listaContatosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);
        setTitle(TITULO_APPBAR);
        listaContatosView = new ListaContatosView(this);
        configuraFabNovoContato();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_contatos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_contatos_menu_remover) {
            listaContatosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoContato() {
        FloatingActionButton botaoNovoContato = findViewById(R.id.activity_lista_contatos_fab_novo_contato);
        botaoNovoContato.setOnClickListener(view -> AbreFormularioModoInsereContato());
    }

    private void AbreFormularioModoInsereContato() {
        startActivity(new Intent(this, FormularioContatoActivity.class));
    }

    protected void onResume() {
        super.onResume();
        listaContatosView.atualizaContatos();
    }

    private void configuraLista() {
        ListView listaDeContatos = findViewById(R.id.activity_lista_de_contatos_listview);
        listaContatosView.configuraAdapter(listaDeContatos);
        configuraListenerDeCliquePorItem(listaDeContatos);
        registerForContextMenu(listaDeContatos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeContatos) {
        listaDeContatos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Contato contatoEscolhido = (Contato) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaContato(contatoEscolhido);
        });
    }

    private void abreFormularioModoEditaContato(Contato contato) {
        Intent vaiParaFormularioActivity = new Intent(ListaContatosActivity.this, FormularioContatoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_CONTATO, contato);
        startActivity(vaiParaFormularioActivity);
    }
}
