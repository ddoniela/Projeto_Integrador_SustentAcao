package com.generation.sustentacao.model

data class Tema(

    var id: Long,
    var descricao: String?,
    var tarefas: List<TarefaEvento?>?

) {

    override fun toString(): String {
        return descricao!!
    }

}