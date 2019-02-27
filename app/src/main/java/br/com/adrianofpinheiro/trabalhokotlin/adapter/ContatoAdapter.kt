package br.com.adrianofpinheiro.trabalhokotlin.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import kotlinx.android.synthetic.main.contato_item.view.*


class ContatoAdapter(
    private val contatos: List<Contato>,
    private val context: Context,
    val listener: (Contato) -> Unit) : Adapter<ContatoAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contato = contatos[position]
        holder?.let {
            it.bindView(contato, listener)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contato_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return contatos.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            contato: Contato,
            listener: (Contato) -> Unit
        ) = with(itemView) {
            val ivJogo = ivJogo
            val tvNome = tvNome
            val tvTelefone = tvTelefone
            val tvEndereco = tvEndereco
            ivJogo.setImageDrawable(ContextCompat.getDrawable(context, contato.id))
            tvNome.text = contato.nome
            tvTelefone.text = contato.telefone.toString()
            tvEndereco.text = contato.endereco
            setOnClickListener { listener(contato) }
        }
    }
}

interface ClickListener {
    fun onClick(view: View, position: Int)
}

