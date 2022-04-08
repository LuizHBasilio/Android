package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Contato;

@Dao
public interface ContatoDAO {

    @Insert
    void salva(Contato luiz);

    @Query("SELECT * FROM contato")
    List<Contato> todos();

    @Delete
    void remove(Contato contatoEscolhido);

    @Update
    void edita(Contato contato);
}
