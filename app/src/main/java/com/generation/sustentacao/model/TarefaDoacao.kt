package com.generation.sustentacao.model

class TarefaDoacao(
    var id: Long,
    var autor: String,
    var produto: String,
    var quantidade: String,
    var descricao: String,
    var entrega: Boolean,
    var data: String
)  {
}