package br.com.adrianofpinheiro.trabalhokotlin.views.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.trabalhokotlin.MovieViewHolder
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import br.com.adrianofpinheiro.trabalhokotlin.model.Movie
import kotlinx.android.synthetic.main.activity_cadastro.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.lista_item.view.*


class ListaAdapter(private val list: List<Movie>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}


//class MainAdapter : RecyclerView.Adapter<CustomViewHolder>(){
//    val lista = listOf("First title", "Second", "3rd", "Moore Title")
//
//    //number of item
//    override fun getItemCount(): Int {
//        return lista.size
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        //create view
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val itemDaLista = layoutInflater.inflate(R.layout.lista_item, parent, false)
//        return CustomViewHolder(itemDaLista)
//    }
//
//    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitle = lista.get(position)
//        holder?.view?.tvNome.text= videoTitle
//    }
//}
//
//class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
//    val video_title = view.tvNome
//}






//    val context: Context,
//    val contatos: List<Contato>,
//    val listener: (Contato) -> Unit,
//    val listenerDelete: (Contato) -> Unit) : RecyclerView.Adapter<MainAdapter.ContatoViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
//            val itemView = LayoutInflater.from(context)
//                .inflate(R.layout.lista_item, parent, false)
//            return ContatoViewHolder(itemView)
//        }
//
//        override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
//            val nota = contatos[position]
//            holder?.let {
//                holder.bindView(nota, listener, listenerDelete)
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return contatos.size
//        }
//
//        class ContatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//            fun bindView(contato: Contato,
//                         listener: (Contato) -> Unit,
//                         listenerDelete: (Contato) -> Unit) = with(itemView) {
//                val ivFoto = ivFoto
//                val inputNome = tvNome
//                val inputTelefone = tvTelefone
//                val inputEndereco = tvEndereco
//                ivFoto.setImageDrawable(ContextCompat.getDrawable(context, contato.id))
//                inputNome.text = contato.nome
//                inputTelefone.text = contato.telefone
//                inputEndereco.text = contato.endereco
//                setOnClickListener { listener(contato) }
//
////                ivDelete.setOnClickListener {
////                    listenerDelete(contato)
////                }
//                setOnClickListener { listener(contato) }
//            }
//        }
//
//}