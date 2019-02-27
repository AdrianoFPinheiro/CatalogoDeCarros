package br.com.adrianofpinheiro.trabalhokotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.adrianofpinheiro.trabalhokotlin.R
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.adrianofpinheiro.trabalhokotlin.adapter.ContatoAdapter
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        rvMeusContatos.adapter = ContatoAdapter(contatos(), this, {
            Log.i("TAG", "Meu CONTATO")
        })
//Grid
//val layoutManager = GridLayoutManager(this, 2)
//Grade escalonável
//val layoutManager = StaggeredGridLayoutManager(
//2, StaggeredGridLayoutManager.VERTICAL)
//Lista na horizontal
//val layoutManager = LinearLayoutManager(this,
//        LinearLayoutManager.HORIZONTAL, false)
//Lista na vertical
        val layoutManager = LinearLayoutManager(this)
        rvMeusContatos.layoutManager = layoutManager
    }

    private fun contatos(): List<Contato> {
        return listOf(
            Contato(
                R.drawable.usuario_logo,
                "Fulano de tal",
                1112344321,
               "Av Paulita, 1100"))

    }
}
