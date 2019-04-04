package br.com.adrianofpinheiro.trabalhokotlin.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.adapter.CarroAdapter
import br.com.adrianofpinheiro.trabalhokotlin.domain.Carro
import br.com.adrianofpinheiro.trabalhokotlin.domain.CarroService
import br.com.adrianofpinheiro.trabalhokotlin.domain.TipoCarro
import br.com.adrianofpinheiro.trabalhokotlin.domain.event.SaveCarroEvent
import br.com.adrianofpinheiro.trabalhokotlin.ui.activitys.CarroActivity
import kotlinx.android.synthetic.main.fragment_carros.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity

open class CarrosFragment : BaseFragment() {
    private var tipo = TipoCarro.classicos
    protected var carros = listOf<Carro>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            tipo = arguments?.getSerializable("tipo") as TipoCarro
        }
        // Registra para receber eventos do bus
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Retorna a view /res/layout/fragment_carros.xml
        return inflater.inflate(R.layout.fragment_carros, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Views
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        // Swipe to Refresh
        swipeToRefresh.setOnRefreshListener {
            taskCarros()
        }
        swipeToRefresh.setColorSchemeResources(
            R.color.refresh_progress_1,
            R.color.refresh_progress_2,
            R.color.refresh_progress_3)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        taskCarros()
    }

    open fun taskCarros() {
        // Mostra uma janela de progresso
        val dialog = ProgressDialog.show(activity, getString(R.string.app_name),
            "Por favor aguarde...", false, true)
        object : Thread() {
            override fun run() {
                // Busca os carros
                carros = CarroService.getCarros(tipo)
                // Atualiza a lista
                activity?.runOnUiThread(Runnable {
                    // Atualiza a lista
                    recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }
                    // Fecha o ProgressDialog
                    dialog.dismiss()
                })
            }
        }.start()
    }


    open fun onClickCarro(carro: Carro) {
        activity?.startActivity<CarroActivity>("carro" to carro)
    }

    @Subscribe
    open fun onRefresh(event: SaveCarroEvent) {
        taskCarros()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancela os eventos do bus
        EventBus.getDefault().unregister(this)
    }
}