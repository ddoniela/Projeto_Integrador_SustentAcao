package com.generation.sustentacao.model

data class Tema (

    var id: Long,
    var descricao: String,
    var tarefas: List<Tarefa>

    ){

}