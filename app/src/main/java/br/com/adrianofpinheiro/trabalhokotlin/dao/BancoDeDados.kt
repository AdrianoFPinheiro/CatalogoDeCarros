package br.com.adrianofpinheiro.trabalhokotlin.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.adrianofpinheiro.trabalhokotlin.model.Contato

@Database(entities = arrayOf(Contato::class), version = 1)
abstract class BancoDeDados : RoomDatabase() {

    abstract fun contatoDAO(): ContatoDAO

    companion object {
        var INSTANCE: BancoDeDados? = null
        fun getDatabase(context: Context): BancoDeDados? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BancoDeDados::class.java,
                    "contatodb"
                )
                    .build()
            }
            return INSTANCE
        }
    }
}