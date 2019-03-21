package br.com.adrianofpinheiro.trabalhokotlin.model

import java.io.Serializable

data class Contato(
    val id: Int
    , val nome: String
    , val telefone: String
    , val endereco: String
//    , val foto: String
):Serializable