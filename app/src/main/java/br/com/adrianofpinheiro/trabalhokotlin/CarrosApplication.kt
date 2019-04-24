package br.com.adrianofpinheiro.trabalhokotlin

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.facebook.stetho.Stetho
import java.lang.IllegalStateException

class CarrosApplication : MultiDexApplication() {
    private val TAG = "CarrosApplication"

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        this.debugConfigs()
    }

    companion object {
        // Singleton da classe Application
        private var appInstance: CarrosApplication? = null

        fun getInstance(): CarrosApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    private fun debugConfigs() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    // Chamado quando o Android finalizar o processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "CarrosApplication.onTerminate()")
    }
}