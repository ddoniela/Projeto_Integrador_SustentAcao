package com.generation.sustentacao.api

import com.generation.sustentacao.model.TarefaDoacao
import com.generation.sustentacao.model.TarefaEvento
import com.generation.sustentacao.model.Tema
import retrofit2.Response

class Repository {

    //Evento
    suspend fun listTemaEvento(): Response<List<Tema>>{
        return RetrofitInstance.api.listTemaEvento()
    }
    suspend fun addTarefaEvento(tarefa: TarefaEvento): Response<TarefaEvento>{
        return RetrofitInstance.api.addTarefaEvento(tarefa)
    }

    suspend fun listTarefaEvento(): Response<List<TarefaEvento>>{
        return RetrofitInstance.api.listTarefaEvento()
    }

    suspend fun deleteTarefaEvento(id: Long): Response<TarefaEvento> {
        return RetrofitInstance.api.deleteTarefaEvento(id)
    }

    suspend fun updatePostagemEvento(tarefa: TarefaEvento): Response<TarefaEvento> {
        return RetrofitInstance.api.updatePostagemEvento(tarefa)
    }


    //Doacao
    suspend fun addTarefaDoacao(tarefaDoacao: TarefaDoacao): Response<TarefaDoacao>{
        return RetrofitInstance.api.addTarefaDoacao(tarefaDoacao)
    }

    suspend fun listTarefaDoacao(): Response<List<TarefaDoacao>>{
        return RetrofitInstance.api.listTarefaDoacao()
    }

    suspend fun updatePostagemDoacao(tarefaDoacao: TarefaDoacao): Response<TarefaDoacao> {
        return RetrofitInstance.api.updatePostagemDoacao(tarefaDoacao)
    }

        suspend fun deleteTarefaDoacao(id: Long): Response<TarefaDoacao> {
            return RetrofitInstance.api.deleteTarefaDoacao(id)
        }
}