package com.generation.sustentacao.api

import com.generation.sustentacao.model.TarefaDoacao
import com.generation.sustentacao.model.TarefaEvento
import com.generation.sustentacao.model.Tema
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("temas")
    suspend fun listTemaEvento(): Response<List<Tema>>

    @POST("postagens")
    suspend fun addTarefaEvento(
        @Body tarefa: TarefaEvento
    ): Response<TarefaEvento>

    @GET("postagens")
    suspend fun listTarefaEvento(): Response<List<TarefaEvento>>

    @PUT("postagens")
    suspend fun updatePostagemEvento(
        @Body tarefa: TarefaEvento
    ): Response<TarefaEvento>

    @DELETE("postagens/{id}")
    suspend fun deleteTarefaEvento(
        @Path("id") id: Long
    ): Response<TarefaEvento>

    //Doacao
    @POST("postagens")
    suspend fun addTarefaDoacao(
        @Body tarefaDoacao: TarefaDoacao
    ): Response<TarefaDoacao>

    @GET("postagens")
    suspend fun listTarefaDoacao(): Response<List<TarefaDoacao>>

    @PUT("postagens")
    suspend fun updatePostagemDoacao(
        @Body tarefaDoacao: TarefaDoacao
    ): Response<TarefaDoacao>

    @DELETE("postagens/{id}")
    suspend fun deleteTarefaDoacao(
        @Path("id") id: Long
    ): Response<TarefaDoacao>

}