package br.com.adrianofpinheiro.trabalhokotlin.domain.event

import br.com.adrianofpinheiro.trabalhokotlin.domain.Carro

data class SaveCarroEvent(val carro: Carro)

data class FavoritoEvent(val carro: Carro)