package br.com.adrianofpinheiro.trabalhokotlin.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.EditText
import br.com.adrianofpinheiro.trabalhokotlin.ListaContatoViewModel
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.dao.BancoDeDados
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import br.com.adrianofpinheiro.trabalhokotlin.ui.NovoContatoDialog
import br.com.adrianofpinheiro.trabalhokotlin.ui.adapter.ListaAdapter
import kotlinx.android.synthetic.main.activity_lista.*
import kotlinx.android.synthetic.main.app_bar_lista.*
import kotlinx.android.synthetic.main.content_lista.*


class ListaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var adapter: ListaAdapter? = null
    private var contatos: List<Contato> = listOf()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_lista)
        setSupportActionBar(toolbar)
        lista_menu_adiciona.setOnClickListener { view ->
            val dialog = NovoContatoDialog()
            dialog.show(fragmentManager, "CriarContato")
        }
        mostrarDados()
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = ListaAdapter(contatos!!)
        recycler_view.adapter = adapter
        val db = BancoDeDados.getDatabase(applicationContext)
        val contato = Contato(
            etNome.text.toString(),
            etTelefone.text.toString()
        )
        if (contato.nome != "")
            InsertAsyncTask(db!!).execute(contato)

    }

    private inner class InsertAsyncTask internal
    constructor(appDatabase: BancoDeDados) : AsyncTask<Contato, Void, String>() {
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Contato): String {
            db.contatoDAO().inserir(params[0])
            return ""
        }
    }

    private fun mostrarDados() {
        //será utilizado
        //get() — indica o ViewModel que será utilizado.
        ViewModelProviders.of(this)
            .get(ListaContatoViewModel::class.java)
            .contatos
            .observe(this, Observer<List<Contato>> { contatos ->
                adapter?.setList(contatos!!)
                recycler_view.adapter.notifyDataSetChanged()
            })
    }

    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText

}

//    val CADASTRO_REQUEST_CODE = 1
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_lista)
//        setSupportActionBar(toolbar)
//
//
//        btCadastrar.setOnClickListener { view ->
//            val cadastroIntent = Intent(
//                this,
//                CadastroActivity::class.java
//            )
//            startActivityForResult(
//                cadastroIntent,
//                CADASTRO_REQUEST_CODE
//            )
//        }
//            fab.setOnClickListener { view ->
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//            }
//
//            val toggle = ActionBarDrawerToggle(
//                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//            )
//            drawer_layout.addDrawerListener(toggle)
//            toggle.syncState()
//
//            nav_view.setNavigationItemSelectedListener(this)
//
//    }
//
//        override fun onBackPressed() {
//            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//                drawer_layout.closeDrawer(GravityCompat.START)
//            } else {
//                super.onBackPressed()
//            }
//        }
//
//        override fun onCreateOptionsMenu(menu: Menu): Boolean {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            menuInflater.inflate(R.menu.lista, menu)
//            return true
//        }
//
////        override fun onOptionsItemSelected(item: MenuItem): Boolean {
////            // Handle action bar item clicks here. The action bar will
////            // automatically handle clicks on the Home/Up button, so long
////            // as you specify a parent activity in AndroidManifest.xml.
////            when (item.itemId) {
////                R.id.action_settings -> return true
////                else -> return super.onOptionsItemSelected(item)
////            }
////        }
//
//        override fun onNavigationItemSelected(item: MenuItem): Boolean {
//            // Handle navigation view item clicks here.
//            when (item.itemId) {
//                R.id.nav_camera -> {
//                    // Handle the camera action
//                }
//                R.id.nav_gallery -> {
//
//                }
//                R.id.nav_slideshow -> {
//
//                }
//                R.id.nav_manage -> {
//
//                }
//                R.id.nav_share -> {
//
//                }
//                R.id.nav_send -> {
//
//                }
//            }
//
//            drawer_layout.closeDrawer(GravityCompat.START)
//            return true
//        }
//
//        private var loadingObserver = Observer<Boolean> {
//            if (it == true) {
//                containerLoading.visibility = View.VISIBLE
//            } else {
//                containerLoading.visibility = View.GONE
//            }
//        }
//
//        private var contatosObserver = Observer<List<Contato>> {
//            preencheALista(it!!)
//        }
//
//        private fun preencheALista(contatos: List<Movie>) {
//            adapter = ListaAdapter(this, contatos,
//                {},
//                {})
//
//            rvMeusContatos.adapter = adapter
//            rvMeusContatos.layoutManager = LinearLayoutManager(this)
//        }
//
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            // Handle action bar item clicks here. The action bar will
//            // automatically handle clicks on the Home/Up button, so long
//            // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> {
//                startActivityForResult(Intent(this, CadastroActivity::class.java), 1)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//            return true
//        }
//
//
//        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            super.onActivityResult(requestCode, resultCode, data)
//
//            /*if(requestCode == FORMULARIO_REQUEST_CODE &&
//                    resultCode == Activity.RESULT_OK) {
//                mainViewModel.buscarTodos()
//            }*/
//            when (requestCode) {
//                CADASTRO_REQUEST_CODE -> {
//                    when (resultCode) {
//                        Activity.RESULT_OK -> {
//                            Toast.makeText(
//                                this,
//                                "Mostra lista",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                        Activity.RESULT_CANCELED -> {
//                            Toast.makeText(
//                                this,
//                                "Cancelou",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//            }
//        }
//
//
//    private fun contatos(): List<Contato> {
//        return listOf(
//            Contato(
//                R.drawable.usuario_logo,
//                "Fulano de tal",
//                "222222222",
//                "Av Paulita, 1100"
//            )
//        )
//
//    }
//



