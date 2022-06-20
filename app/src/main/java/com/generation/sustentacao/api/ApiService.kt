package com.generation.sustentacao.api

import com.generation.sustentacao.model.Tarefa
import com.generation.sustentacao.model.Tema
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("temas")
    suspend fun listTema(): Response<List<Tema>>

    @POST("postagens")
    suspend fun addTarefa(
        @Body tarefa: Tarefa
    ): Response<Tarefa>

    @GET("postagens")
    suspend fun listTarefa(): Response<List<Tarefa>>

}