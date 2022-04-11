package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

abstract class BaseContatoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    BaseContatoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    void vinculaContatoComTelefone(int contatoId, Telefone... telefones) {
        for (Telefone telefone :
                telefones) {
            telefone.setContatoId(contatoId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }

}
