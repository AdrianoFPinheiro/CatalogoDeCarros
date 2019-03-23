package br.com.adrianofpinheiro.trabalhokotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_home.*

import android.support.v7.recyclerview.extensions.ListAdapter
import br.com.adrianofpinheiro.trabalhokotlin.model.Movie


class FragmentHome : Fragment() {

    /**
     * Initialize newInstance for passing parameters
     */
//    companion object {
//        fun newInstance(): FragmentHome {
//            val fragmentHome = FragmentHome()
//            val args = Bundle()
//            fragmentHome.arguments = args
//            return fragmentHome
//        }
//
//    }

    private val mNicolasCageMovies = listOf(
        Movie("Raising Arizona", 1987),
        Movie("Vampire's Kiss", 1988),
        Movie("Con Air", 1997),
        Movie("Gone in 60 Seconds", 1997),
        Movie("National Treasure", 2004),
        Movie("The Wicker Man", 2006),
        Movie("Ghost Rider", 2007),
        Movie("Knowing", 2009)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(br.com.adrianofpinheiro.trabalhokotlin.R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = br.com.adrianofpinheiro.trabalhokotlin.views.adapter.ListaAdapter(mNicolasCageMovies)
        }
    }

    companion object {
        fun newInstance(): FragmentHome = FragmentHome()

    }
}