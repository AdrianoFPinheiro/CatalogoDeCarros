package br.com.adrianofpinheiro.trabalhokotlin.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.trabalhokotlin.R
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentHome : Fragment() {

    /**
     * Initialize newInstance for passing parameters
     */
    companion object {
        fun newInstance(): FragmentHome {
            val fragmentHome = FragmentHome()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

}