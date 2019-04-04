package br.com.adrianofpinheiro.trabalhokotlin.fragments

import br.com.adrianofpinheiro.trabalhokotlin.adapter.CarroAdapter
import br.com.adrianofpinheiro.trabalhokotlin.domain.Carro
import br.com.adrianofpinheiro.trabalhokotlin.domain.FavoritosService
import br.com.adrianofpinheiro.trabalhokotlin.domain.event.FavoritoEvent
import br.com.adrianofpinheiro.trabalhokotlin.ui.activitys.CarroActivity
import kotlinx.android.synthetic.main.fragment_carros.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class FavoritosFragment : CarrosFragment() {

    @Suppress("UNUSED_PARAMETER")
    @Subscribe
    fun onRefresh(event: FavoritoEvent) {
        taskCarros()
    }

    override fun taskCarros() {
        doAsync {
            // Busca os carros
            carros = FavoritosService.getCarros()
            uiThread {
                recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }

                // Termina a animação do Swipe to Refresh
                swipeToRefresh.isRefreshing = false
            }
        }
    }

    override fun onClickCarro(carro: Carro) {
        // Ao clicar no carro vamos navegar para a tela de detalhes
        activity?.startActivity<CarroActivity>("carro" to carro)
    }
}
