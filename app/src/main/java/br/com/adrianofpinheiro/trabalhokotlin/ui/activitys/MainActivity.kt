package br.com.adrianofpinheiro.trabalhokotlin.ui.activitys

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.adapter.TabsAdapter
import br.com.adrianofpinheiro.trabalhokotlin.domain.TipoCarro
import br.com.adrianofpinheiro.trabalhokotlin.extensions.setupToolbar
import br.com.adrianofpinheiro.trabalhokotlin.extensions.toast
import br.com.adrianofpinheiro.trabalhokotlin.ui.LoginActivity
import br.com.adrianofpinheiro.trabalhokotlin.ui.activitys.dialogs.SobreActivity
import br.com.adrianofpinheiro.trabalhokotlin.utils.Prefs
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.maxcruz.reactivePermissions.entity.Permission
import com.maxcruz.reactivePermissions.ReactivePermissions
import org.jetbrains.anko.*


class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener {

    val REQUEST_CODE = 554
    val reacPermissions = ReactivePermissions(this, REQUEST_CODE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
        setupNavDrawer()
        setupViewPagerTabs()

        // FAB (variável fab gerada automaticamente pelo Kotlin Extensions)
        fab.setOnClickListener() {
            startActivity<CarroFormActivity>()
        }
    }

    // Configura o Navigation Drawer
    private fun setupNavDrawer() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }
    private fun setupViewPagerTabs() {
        // Configura o ViewPager + Tabs
        // As variáveis viewPager e tabLayout são geradas automaticamente pelo Kotlin Extensions
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
        // Cor branca no texto (o fundo azul é definido no layout)
        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)

        // Salva e Recupera a última Tab acessada.
        val tabIdx = Prefs.tabIdx

        viewPager.currentItem = tabIdx
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) { }
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) { }
            override fun onPageSelected(page: Int) {
                // Salva o índice da página/tab selecionada
                Prefs.tabIdx = page
            }
        })
    }

    // Trata o evento do Navigation Drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.classicos)
            }
            R.id.nav_item_carros_esportivos -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.esportivos)
            }
            R.id.nav_item_carros_luxo -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.luxo)
            }

            R.id.nav_item_ligar_contato -> {
                makeCallPhone()
            }

            R.id.nav_item_compartilhar -> {
                share("https://play.google.com/store/apps/details?id=br.com.adrianofpinheiro.trabalhokotlin", "FIAP[2019]")
            }
            R.id.nav_item_about -> {
                val intent = Intent(this@MainActivity, SobreActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_item_exit -> {
                    alert(R.string.msg_confirma_logout, R.string.app_name) {
                        positiveButton(R.string.sim) {
                            // Confirmou em deslogar
                            FirebaseAuth.getInstance().signOut()
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        negativeButton(R.string.nao) {
                            // Não confirmou...
                        }
                    }.show()

            }
        }
        // Fecha o menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {

        if (requestCode == REQUEST_CODE){
            reacPermissions.receive(permissions, grantResults)
        }
    }

    @SuppressLint("CheckResult")
    private fun makeCallPhone(){
        val phone = Permission(
            Manifest.permission.CALL_PHONE,
            R.string.explicacao_permissao,
            true
        )

        val permissions = listOf( phone )

        reacPermissions.observeResultPermissions().subscribe{
                event ->
            if (event.first == Manifest.permission.CALL_PHONE
                && event.second) {

                makeCall("0800 724 8154")
            }
            else if (event.first == Manifest.permission.SEND_SMS
                && event.second) {
                sendSMS("0800 724 8154")
            }
        }

        reacPermissions.evaluate(permissions)
    }


}
