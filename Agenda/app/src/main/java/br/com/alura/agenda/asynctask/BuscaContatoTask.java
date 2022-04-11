package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.ContatoDAO;
import br.com.alura.agenda.model.Contato;
import br.com.alura.agenda.ui.adapter.ListaContatosAdapter;

public class BuscaContatoTask extends AsyncTask<Void, Void, List<Contato>> {

    private final ContatoDAO contatoDAO;
    private final ListaContatosAdapter adapter;

    public BuscaContatoTask(ContatoDAO contatoDAO, ListaContatosAdapter adapter) {
        this.contatoDAO = contatoDAO;
        this.adapter = adapter;
    }

    @Override
    protected List<Contato> doInBackground(Void[] objects) {
        return contatoDAO.todos();
    }

    @Override
    protected void onPostExecute(List<Contato> todosContatos) {
        super.onPostExecute(todosContatos);
        adapter.atualiza((List<Contato>) todosContatos);
    }
}
