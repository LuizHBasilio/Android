package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.ContatoDAO;
import br.com.alura.agenda.model.Contato;
import br.com.alura.agenda.ui.adapter.ListaContatosAdapter;

public class RemoveContatoTask extends AsyncTask<Void, Void, Void> {


    private final ContatoDAO contatoDAO;
    private final ListaContatosAdapter adapter;
    private final Contato contato;

    public RemoveContatoTask(ContatoDAO contatoDAO, ListaContatosAdapter adapter, Contato contato) {
        this.contatoDAO = contatoDAO;
        this.adapter = adapter;
        this.contato = contato;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        contatoDAO.remove(contato);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter.remove(contato);
    }
}
