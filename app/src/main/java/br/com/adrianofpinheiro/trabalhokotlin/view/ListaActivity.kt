package br.com.adrianofpinheiro.trabalhokotlin.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.adrianofpinheiro.trabalhokotlin.R
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.adrianofpinheiro.trabalhokotlin.adapter.ContatoAdapter
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        //conteudo da lista
        rvMeusContatos.adapter = ContatoAdapter(contatos(), this, {
            Log.i("TAG", "Meu CONTATO")
        })

        val layoutManager = LinearLayoutManager(this)
        rvMeusContatos.layoutManager = layoutManager

        btCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

    }

    private fun contatos(): List<Contato> {
        return listOf(
            Contato(
                R.drawable.usuario_logo,
                "Fulano de tal",
                1112344321,
                "Av Paulita, 1100"
            )
        )

    }


}
