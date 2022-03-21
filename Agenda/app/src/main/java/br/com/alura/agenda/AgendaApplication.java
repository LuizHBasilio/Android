package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.DAO.ContatoDAO;
import br.com.alura.agenda.model.Contato;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        ContatoDAO contatoDAO = new ContatoDAO();
        contatoDAO.salva(new Contato("Luiz", "985731948", "luiz@gmail.com"));
        contatoDAO.salva(new Contato("Marcos", "998756645", "marcos@gmail.com"));
    }
}
