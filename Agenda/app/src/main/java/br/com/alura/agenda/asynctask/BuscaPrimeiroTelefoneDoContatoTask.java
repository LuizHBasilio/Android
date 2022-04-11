package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoContatoTask extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO telefoneDAO;
    private final int contatoId;
    private final PrimeiroTelefoneEncontrandoListener listener;

    public BuscaPrimeiroTelefoneDoContatoTask(TelefoneDAO telefoneDAO,
                                              int contatoId, PrimeiroTelefoneEncontrandoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.contatoId = contatoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefoneDoContato(contatoId);
    }

    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontrandoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
