package br.com.adrianofpinheiro.trabalhokotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import br.com.adrianofpinheiro.trabalhokotlin.dao.BancoDeDados
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato

open class ListaContatoViewModel(application: Application): AndroidViewModel(application) {

    lateinit var contatos: LiveData<List<Contato>>
    private val bd: BancoDeDados =
        BancoDeDados.getDatabase(application.applicationContext)!!
    init{
        carregarDados()
    }
    private fun carregarDados() {
        //Carregar os dados da nossa Base de dados e armazenar no LiveData
        contatos = bd.contatoDAO().lerContato()
    }
}