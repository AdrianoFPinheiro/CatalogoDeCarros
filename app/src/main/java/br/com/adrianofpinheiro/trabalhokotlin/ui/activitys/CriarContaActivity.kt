package br.com.adrianofpinheiro.trabalhokotlin.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.domain.Usuario
import br.com.adrianofpinheiro.trabalhokotlin.extensions.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_criar_conta.*


class CriarContaActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_conta)

        mAuth = FirebaseAuth.getInstance()

        btCreate.setOnClickListener {
            if(!inputEmail.text.isNullOrEmpty() && !inputPassword.text.isNullOrEmpty()) {
                mAuth.createUserWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        saveInRealTimeDatabase()
                    } else {
                        Toast.makeText(this@CriarContaActivity, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                inputEmail.error = getString(R.string.msg_error_form_email)
                inputPassword.error = getString(R.string.msg_error_form_password)
            }
        }
    }

    private fun saveInRealTimeDatabase() {
        val user = Usuario(inputEmail.text.toString())
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    toast(getString(R.string.msg_success))
                    val returnIntent = Intent(this@CriarContaActivity, LoginActivity::class.java)
                    returnIntent.putExtra("email", inputEmail.text.toString())
                    setResult(RESULT_OK, returnIntent)
                    finish()
                } else {
                    toast(getString(R.string.msg_fail))
                }
            }
    }

}

