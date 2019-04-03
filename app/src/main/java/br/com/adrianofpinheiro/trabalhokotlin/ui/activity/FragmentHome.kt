package br.com.adrianofpinheiro.trabalhokotlin.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat

import android.support.v4.app.Fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.Toast
import br.com.adrianofpinheiro.trabalhokotlin.*
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import br.com.adrianofpinheiro.trabalhokotlin.ui.adapter.ListaAdapter
import br.com.adrianofpinheiro.trabalhokotlin.util.CarregaImagem
import com.airbnb.lottie.L.TAG
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.content_lista.*
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome : Fragment() {

    private var adapter: ListaAdapter? = null
    private var contatos: List<Contato> = listOf()

//    private fun mostrarDados() {
//        //será utilizado
//        //get() — indica o ViewModel que será utilizado.
//        ViewModelProviders.of(this)
//            .get(ListaContatoViewModel::class.java)
//            .contatos
//            .observe(this, Observer<List<Contato>> { contatos ->
//                adapter?.setList(contatos!!)
//                recycler_view.adapter.notifyDataSetChanged()
//            })
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
//        mostrarDados()

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
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(br.com.adrianofpinheiro.trabalhokotlin.R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        recycler_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = br.com.adrianofpinheiro.trabalhokotlin.ui.adapter.ListaAdapter(contatos)
//        }

        btAgenda.setOnClickListener {
            activity?.let {
                val intent = Intent(it, CadastroActivity::class.java)
                it.startActivity(intent)
            }
            Log.d(TAG, "BOTÃO AGENDA CLICADO")
        }

        btImagem.setOnClickListener {
            activity?.let {
                val intent = Intent(it, CadastroActivity::class.java)
                it.startActivity(intent)
            }

            Log.d(TAG, "BOTÃO IMAGEM CLICADO")
        }

        btMapa.setOnClickListener {
            activity?.let {
                val intent = Intent(it, MapsActivity::class.java)
                it.startActivity(intent)
            }
            Log.d(TAG, "BOTÃO MAPA CLICADO")
        }

        ivFoto.setOnClickListener(View.OnClickListener {
          //  CarregaImagem().verificaPermissao()
        })
    }

    companion object {
        fun newInstance(): FragmentHome = FragmentHome()

    }

//
//    val READIMAGE: Int = 253
//    fun verificaPermissao() {
//        if (Build.VERSION.SDK_INT >= 23) {
//
//            if (ActivityCompat.checkSelfPermission
//                    (this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED
//            ) {
//                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READIMAGE)
//                return
//            }
//
//        }
//        carregaImagem()
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//
//        when (requestCode) {
//            READIMAGE -> {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    carregaImagem()
//                } else {
//                    Toast.makeText(this, "Você não pode acessar as sua fotos", Toast.LENGTH_LONG).show()
//                }
//            }
//            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        }
//
//
//    }
//
//    val ESCOLHE_IMAGEM_CODE = 123
//    fun carregaImagem() {
//        var intent = Intent(
//            Intent.ACTION_PICK,
//            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        )
//        startActivityForResult(intent, ESCOLHE_IMAGEM_CODE)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == ESCOLHE_IMAGEM_CODE && data != null && resultCode == Activity.RESULT_OK) {
//
//            val imagemSelecionada = data.data
//            val caminhoDoArquivo = arrayOf(MediaStore.Images.Media.DATA)
//            val cursor = contentResolver.query(imagemSelecionada, caminhoDoArquivo, null, null, null)
//            cursor.moveToFirst()
//
//            val columnIndex = cursor.getColumnIndex(caminhoDoArquivo[0])
//            val caminhoDaImagem = cursor.getString(columnIndex)
//            cursor.close()
//            ivFoto.setImageBitmap(BitmapFactory.decodeFile(caminhoDaImagem))
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        /*if(requestCode == FORMULARIO_REQUEST_CODE &&
//                resultCode == Activity.RESULT_OK) {
//            mainViewModel.buscarTodos()
//        }*/
//        when (requestCode) {
//            CADASTRO_REQUEST_CODE -> {
//                when (resultCode) {
//                    Activity.RESULT_OK -> { Toast.makeText(this, "Mostra lista", Toast.LENGTH_LONG).show()
//                    }
//                    Activity.RESULT_CANCELED -> { Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }

}

