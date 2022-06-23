package com.generation.sustentacao.api

import com.generation.sustentacao.model.Tarefa
import com.generation.sustentacao.model.Tema
import retrofit2.Response

class Repository {

    suspend fun listTema(): Response<List<Tema>>{
        return RetrofitInstance.api.listTema()
    }
    suspend fun addTarefa(tarefa: Tarefa): Response<Tarefa>{
        return RetrofitInstance.api.addTarefa(tarefa)
    }

    suspend fun listTarefa(): Response<List<Tarefa>>{
        return RetrofitInstance.api.listTarefa()
    }

    suspend fun updatePostagem(tarefa: Tarefa): Response<Tarefa> {
        return RetrofitInstance.api.updatePostagem(tarefa)
    }

}