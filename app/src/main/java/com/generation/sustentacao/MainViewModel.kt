package com.generation.sustentacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.sustentacao.api.Repository
import com.generation.sustentacao.model.Tarefa
import com.generation.sustentacao.model.Tema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val repository: Repository
        ) : ViewModel(){

    private val _myTemaResponse =
        MutableLiveData<Response<List<Tema>>>()

    val myTemaResponse: LiveData<Response<List<Tema>>> =
        _myTemaResponse

    val dataSelecionada = MutableLiveData<LocalDate>()

    init{

    }

    fun listTema(){
        viewModelScope.launch {
            try{
                val response = repository.listTema()
                _myTemaResponse.value = response

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }

    }

    fun addTarefa(tarefa: Tarefa){
        viewModelScope.launch {
            try {
                repository.addTarefa(tarefa)

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())

            }
        }
    }
}