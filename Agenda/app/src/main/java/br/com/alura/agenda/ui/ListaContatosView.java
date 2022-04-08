package br.com.alura.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.ContatoDAO;
import br.com.alura.agenda.model.Contato;
import br.com.alura.agenda.ui.adapter.ListaContatosAdapter;

public class ListaContatosView {

    private final ListaContatosAdapter adapter;
    private final ContatoDAO contatoDAO;
    private final Context context;

    public ListaContatosView(Context context) {
        this.context = context;
        this.adapter = new ListaContatosAdapter(this.context);
        contatoDAO = AgendaDatabase.getInstance(context)
                .getRoomAlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo Contato")
                .setMessage("Deseja remover o contato?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Contato contatoEscolhido = adapter.getItem(menuInfo.position);
                    remove(contatoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaContatos() {
        adapter.atualiza(contatoDAO.todos());
    }

    private void remove(Contato contatoEscolhido) {
        contatoDAO.remove(contatoEscolhido);
        adapter.remove(contatoEscolhido);
    }

    public void configuraAdapter(ListView listaDeContatos) {
        listaDeContatos.setAdapter(adapter);
    }
}
