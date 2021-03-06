package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.BuscaPrimeiroTelefoneDoContatoTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Contato;

public class ListaContatosAdapter extends BaseAdapter {

    private final List<Contato> contatos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO telefoneDAO;

    public ListaContatosAdapter(Context context) {
        this.context = context;
        telefoneDAO = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int posicao) {
        return contatos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return contatos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Contato contatoDevolvido = contatos.get(posicao);
        vincula(viewCriada, contatoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Contato contato) {
        TextView nome = view.findViewById(R.id.item_contato_nome);
        nome.setText(contato.getNome());
        TextView telefone = view.findViewById(R.id.item_contato_telefone);
        new BuscaPrimeiroTelefoneDoContatoTask(telefoneDAO, contato.getId(),
                telefoneEncontrado ->
            telefone.setText(telefoneEncontrado.getNumero())).execute();
        TextView email = view.findViewById(R.id.item_contato_email);
        email.setText(contato.getEmail());

    }


    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_contato, viewGroup, false);
    }

    public void atualiza(List<Contato> contatos) {
        this.contatos.clear();
        this.contatos.addAll(contatos);
        notifyDataSetChanged();
    }

    public void remove(Contato contato) {
        contatos.remove(contato);
        notifyDataSetChanged();
    }
}
