package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Contato;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesDoContatoTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Contato contato;
    private final TelefonesDoContatoEncontradosListener listener;

    public BuscaTodosTelefonesDoContatoTask(TelefoneDAO telefoneDAO,
                                            Contato contato,
                                            TelefonesDoContatoEncontradosListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.contato = contato;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO
                .buscaTodosTelefonesDoContato(contato.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesDoContatoEncontradosListener {
        void quandoEncontrados(List<Telefone> telefones);
    }

}
