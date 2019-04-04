package br.com.adrianofpinheiro.trabalhokotlin.domain.retrofit

import br.com.adrianofpinheiro.trabalhokotlin.domain.Carro
import br.com.adrianofpinheiro.trabalhokotlin.domain.Response
import retrofit2.Call
import retrofit2.http.*

interface CarrosREST {

    @GET("tipo/{tipo}")
    fun getCarros(@Path("tipo") tipo: String): Call<List<Carro>>

    @POST("./")
    fun save(@Body carro: Carro): Call<Response>

    @DELETE("{id}")
    fun delete(@Path("id") id: Long): Call<Response>

    @FormUrlEncoded
    @POST("postFotoBase64")
    fun postFoto(@Field("fileName") fileName:String, @Field("base64") base64:String): Call<Response>
}