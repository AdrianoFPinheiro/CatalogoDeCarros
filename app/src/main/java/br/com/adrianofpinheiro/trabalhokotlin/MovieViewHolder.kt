package br.com.adrianofpinheiro.trabalhokotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import br.com.adrianofpinheiro.trabalhokotlin.model.Movie

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.lista_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.tvNome)
        mYearView = itemView.findViewById(R.id.tvTelefone)
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.title
        mYearView?.text = movie.year.toString()
    }

}