package com.generation.sustentacao.api

import com.generation.sustentacao.model.Tema
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("temas")
    suspend fun listTema(): Response<List<Tema>>

}