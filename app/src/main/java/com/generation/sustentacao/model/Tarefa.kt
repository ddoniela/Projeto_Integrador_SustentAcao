package com.generation.sustentacao.model

data class Tarefa(
    var id: Long,
    var nomeEvento: String,
    var autor: String,
    var descricao: String,
    var link: String,
    var dataHora: String,
    var tema: Tema
        ) {
}