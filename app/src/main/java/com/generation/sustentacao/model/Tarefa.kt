package com.generation.sustentacao.model

data class Tarefa(
    var nomeEvento: String,
    var autor: String,
    var descricao: String,
    var link: String,
    var tema: Tema
        ) {
}