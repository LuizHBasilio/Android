package br.com.alura.agenda.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Contato;

public class ContatoDAO {

    private final static List<Contato> contatos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Contato contato) {
        contato.setId(contadorDeIds);
        contatos.add(contato);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Contato contato) {
        Contato contatoEncontrado = buscaContatoPorId(contato);
        if (contatoEncontrado != null) {
            int posicaoDoContato = contatos.indexOf(contatoEncontrado);
            contatos.set(posicaoDoContato, contato);
        }
    }

    private Contato buscaContatoPorId(Contato contato) {
        for (Contato a :
                contatos) {
            if (a.getId() == contato.getId()) {
                return a;
            }
        }
        return null;
    }

    public static List<Contato> todos() {
        return new ArrayList<>(contatos);
    }

    public void remove(Contato contato) {
        Contato contatoDevolvido = buscaContatoPorId(contato);
        if (contatoDevolvido != null) {
            contatos.remove(contatoDevolvido);
        }
    }
}
