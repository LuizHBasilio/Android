package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.database.dao.ContatoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Contato;
import br.com.alura.agenda.model.Telefone;

public class SalvaContatoTask extends BaseContatoComTelefoneTask {

    private final ContatoDAO contatoDAO;
    private final Contato contato;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;

    public SalvaContatoTask(ContatoDAO contatoDAO, Contato contato, Telefone telefoneFixo,
                            Telefone telefoneCelular, TelefoneDAO telefoneDAO, FinalizadaListener listener) {
        super(listener);
        this.contatoDAO = contatoDAO;
        this.contato = contato;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int contatoId = contatoDAO.salva(contato).intValue();
        vinculaContatoComTelefone(contatoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
        return null;
    }
}
