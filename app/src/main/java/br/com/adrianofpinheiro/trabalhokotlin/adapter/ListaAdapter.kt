package br.com.adrianofpinheiro.trabalhokotlin.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.domain.Contato
import kotlinx.android.synthetic.main.activity_cadastro.view.*
import kotlinx.android.synthetic.main.contato_item.view.*

class ListaAdapter(
    val context: Context,
    val contatos: List<Contato>,
    val listener: (Contato) -> Unit,
    val listenerDelete: (Contato) -> Unit) : RecyclerView.Adapter<ListaAdapter.ContatoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
            val itemView = LayoutInflater.from(context)
                .inflate(R.layout.contato_item, parent, false)
            return ContatoViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
            val nota = contatos[position]
            holder?.let {
                holder.bindView(nota, listener, listenerDelete)
            }
        }

        override fun getItemCount(): Int {
            return contatos.size
        }

        class ContatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindView(contato: Contato,
                         listener: (Contato) -> Unit,
                         listenerDelete: (Contato) -> Unit) = with(itemView) {
                val ivFoto = ivFoto
                val inputNome = tvNome
                val inputTelefone = tvTelefone
                val inputEndereco = tvEndereco
                ivFoto.setImageDrawable(ContextCompat.getDrawable(context, contato.id))
                inputNome.text = contato.nome
                inputTelefone.text = contato.telefone
                inputEndereco.text = contato.endereco
                setOnClickListener { listener(contato) }

//                ivDelete.setOnClickListener {
//                    listenerDelete(contato)
//                }
                setOnClickListener { listener(contato) }
            }
        }

}