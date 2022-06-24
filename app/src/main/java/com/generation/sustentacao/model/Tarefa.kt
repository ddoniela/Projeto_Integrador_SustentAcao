package com.generation.sustentacao.model

data class Tarefa(
    var id: Long,
    var titulo: String,
    var descricao: String,
    var imagem: String,
    var dataHora: String,
    var autor: String,
    var tema: Tema
        ) {
}