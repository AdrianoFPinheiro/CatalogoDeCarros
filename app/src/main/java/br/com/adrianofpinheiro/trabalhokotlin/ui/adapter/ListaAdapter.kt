package br.com.adrianofpinheiro.trabalhokotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import kotlinx.android.synthetic.main.lista_item.view.*


class ListaAdapter(var contatos: List<Contato>) :
    RecyclerView.Adapter<ListaAdapter.ContatoViewHolder>() {
    override fun getItemCount(): Int {
        return contatos.size
    }
    fun setList(contatos: List<Contato>) {
        this.contatos = contatos
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ContatoViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_item, parent, false)
        return ContatoViewHolder(v)
    }
    override fun onBindViewHolder(holder: ContatoViewHolder, i: Int)
    {
        val contato = contatos[i]
        holder.tvNome.text = contato.nome
        holder.tvTelefone.text = contato.telefone
    }
    class ContatoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvNome: TextView = v.findViewById(R.id.tvNome)
        var tvTelefone: TextView = v.findViewById(R.id.tvTelefone)
    }
}


