package br.com.adrianofpinheiro.trabalhokotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
class Contato {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var nome: String? = null
    var telefone: String? = null
    var endereco: String? = null
//     val foto: String

    constructor() {}

    constructor (nome: String, telefone: String) {
        this.nome = nome
        this.telefone = telefone
    }

    constructor(id: Int, nome: String, telefone: String) {
        this.id = id
        this.nome = nome
        this.telefone = telefone
    }

}