package br.com.adrianofpinheiro.trabalhokotlin.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.widget.EditText
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.dao.BancoDeDados
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato
import kotlinx.android.synthetic.main.novo_contato_dialog.*

class NovoContatoDialog : DialogFragment() {

    private lateinit var builder: AlertDialog.Builder
    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        builder = AlertDialog.Builder(activity)
        val v = activity.layoutInflater.inflate(R.layout.novo_contato_dialog, null)
        etNome = v.findViewById(R.id.etNome)
        etTelefone = v.findViewById(R.id.etTelefone)
        builder.setView(v)
        builder.setTitle("Novo Contato")
        builder.setPositiveButton("Adicionar") { _, _ ->
            val db = BancoDeDados.getDatabase(activity.applicationContext)
            val contato = Contato(
                etNome.text.toString(),
                etTelefone.text.toString()
            )
            if (contato.nome != "")
                InsertAsyncTask(db!!).execute(contato)
        }
        builder.setNegativeButton("Cancelar", null)
        return builder.create()
    }


    private inner class InsertAsyncTask internal
    constructor(appDatabase: BancoDeDados) : AsyncTask<Contato, Void, String>() {
        private val db: BancoDeDados = appDatabase
        override fun doInBackground(vararg params: Contato): String {
            db.contatoDAO().inserir(params[0])
            return ""
        }
    }
}