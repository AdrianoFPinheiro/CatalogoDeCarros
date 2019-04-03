package br.com.adrianofpinheiro.trabalhokotlin.util

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.adrianofpinheiro.trabalhokotlin.api.ResponseStatus
import br.com.adrianofpinheiro.trabalhokotlin.ui.FragmentHome
import kotlinx.android.synthetic.main.fragment_home.*

class CarregaImagem : AppCompatActivity(){

    private var responseStatusObserver = Observer<ResponseStatus> {
        if(it?.successo == true) {
            setResult(Activity.RESULT_OK)
            finish()
        } else {
            Toast.makeText(this,
                it?.mensagem,
                Toast.LENGTH_SHORT).show()
        }
    }


    val READIMAGE: Int = 253
    fun verificaPermissao() {
        if (Build.VERSION.SDK_INT >= 23) {

            if (ActivityCompat.checkSelfPermission
                    (this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READIMAGE)
                return
            }

        }
        carregaImagem()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            READIMAGE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    carregaImagem()
                } else {
                    Toast.makeText(this, "Você não pode acessar as sua fotos", Toast.LENGTH_LONG).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    val ESCOLHE_IMAGEM_CODE = 123
    fun carregaImagem() {
        var intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(intent, ESCOLHE_IMAGEM_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ESCOLHE_IMAGEM_CODE && data != null && resultCode == Activity.RESULT_OK) {

            val imagemSelecionada = data.data
            val caminhoDoArquivo = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(imagemSelecionada, caminhoDoArquivo, null, null, null)
            cursor.moveToFirst()

            val columnIndex = cursor.getColumnIndex(caminhoDoArquivo[0])
            val caminhoDaImagem = cursor.getString(columnIndex)
            cursor.close()
            //Seta a imagem
            ivFoto.setImageBitmap(BitmapFactory.decodeFile(caminhoDaImagem))
        }
    }


}