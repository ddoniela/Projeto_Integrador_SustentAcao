package com.generation.sustentacao.model

data class Tarefa (
    var nomeEvento: String,
    var nomedaOng: String,
    var descricao: String,
    var link: String,
    var data: String,
    var autor: String,
    var tema: Tema
        ) {
}