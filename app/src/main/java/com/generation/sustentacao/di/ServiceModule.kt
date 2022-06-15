package com.generation.sustentacao.di

import com.generation.sustentacao.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides

    fun returnRepository(): Repository{
        return Repository()
    }
}