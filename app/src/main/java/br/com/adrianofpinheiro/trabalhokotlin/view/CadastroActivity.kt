package br.com.adrianofpinheiro.trabalhokotlin.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Toast
import br.com.adrianofpinheiro.trabalhokotlin.R
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        ivFoto.setOnClickListener(View.OnClickListener {
            verificaPermissao()
        })

        btMapa.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        btCadastrar.setOnClickListener{
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
            finish()
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
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
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
            ivFoto.setImageBitmap(BitmapFactory.decodeFile(caminhoDaImagem))
        }
    }


}










