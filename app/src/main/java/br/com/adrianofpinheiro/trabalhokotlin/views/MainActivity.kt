package br.com.adrianofpinheiro.trabalhokotlin.views

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import br.com.adrianofpinheiro.trabalhokotlin.R
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_lista -> {

                val fragment = FragmentHome.Companion.newInstance()
                addFragment(fragment)
//                val intent = Intent(this@MainActivity, ListaActivity::class.java)
//                startActivity(intent)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_sobre -> {
                val fragment = FragmentSobre()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sair -> {
                FirebaseAuth.getInstance().signOut()
                finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content = findViewById(R.id.content)
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = FragmentHome.Companion.newInstance()
        addFragment(fragment)
    }


    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
