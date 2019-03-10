package br.com.adrianofpinheiro.trabalhokotlin.domain

import retrofit2.Call
import retrofit2.http.*

interface ContatoAPI {

    interface NotaAPI {

        @GET("/contato")
        fun buscarTodos(): Call<List<Contato>>

        @GET("/contato/nome/{nome}")
        fun buscarPorNome(@Path("nome") nome: String): Call<List<Contato>>

        @POST("/contato")
        fun salvar(@Body contato: Contato): Call<Contato>

        @DELETE("/contato/{id}")
        fun apagar(@Path("id") id: String): Call<Void>

    }
}