package br.com.adrianofpinheiro.trabalhokotlin.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato

@Dao
interface ContatoDAO {

    @Insert
    fun inserir(contato: Contato)

    @Query("SELECT * FROM Contato")
    fun lerContato(): LiveData<List<Contato>>

    @Query("SELECT * FROM Contato WHERE id = :id")
    fun buscarPor(id: Int): Contato

    @Update
    fun atualizar(contato: Contato)

    @Delete
    fun apagar(contato: Contato)
}