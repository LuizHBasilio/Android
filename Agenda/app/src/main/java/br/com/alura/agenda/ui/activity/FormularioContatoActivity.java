package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_CONTATO;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.ContatoDAO;
import br.com.alura.agenda.model.Contato;

public class FormularioContatoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_CONTATO = "Novo Contato";
    private static final String TITULO_APPBAR_EDITA_CONTATO = "Edita Contato";
    private EditText campoNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private ContatoDAO contatoDAO;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contato);
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        contatoDAO = database.getRoomAlunoDAO();
        inicializacaoDosCampos();
        carregaContato();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_contato_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_contato_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaContato() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_CONTATO)) {
            setTitle(TITULO_APPBAR_EDITA_CONTATO);
            contato = (Contato) dados.getSerializableExtra(CHAVE_CONTATO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_CONTATO);
            contato = new Contato();
        }
    }

    private void preencheCampos() {
        campoNome.setText(contato.getNome());
        campoTelefoneFixo.setText(contato.getTelefoneFixo());
        campoTelefoneCelular.setText(contato.getTelefoneCelular());
        campoEmail.setText(contato.getEmail());
    }

    private void finalizaFormulario() {
        preencheContato();
        if (contato.temIdValido()) {
            contatoDAO.edita(contato);
        } else {
            contatoDAO.salva(contato);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_contato_nome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_contato_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_contato_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_contato_email);

    }

    private void preencheContato() {
        String nome = campoNome.getText().toString();
        String telefoneFixo = campoTelefoneFixo.getText().toString();
        String telefoneCelular = campoTelefoneCelular.getText().toString();
        String email = campoEmail.getText().toString();

        contato.setNome(nome);
        contato.setTelefoneFixo(telefoneFixo);
        contato.setTelefoneCelular(telefoneCelular);
        contato.setEmail(email);
    }
}