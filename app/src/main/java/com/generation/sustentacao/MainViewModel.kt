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
import java.time.LocalDate
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class MainViewModel @Inject constructor(
   private val repository: Repository
        ) : ViewModel(){

    var postagemSelecionada: Tarefa? = null

    private val _myTemaResponse =
        MutableLiveData<Response<List<Tema>>>()

    val myTemaResponse: LiveData<Response<List<Tema>>> =
        _myTemaResponse

    private val _myTarefaResponse =
        MutableLiveData<Response<List<Tarefa>>>()

    val myTarefaResponse: LiveData<Response<List<Tarefa>>> =
        _myTarefaResponse

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
               val response = repository.addTarefa(tarefa)
                Log.d("Opa", response.body().toString())
                listTarefa()

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listTarefa(){
        viewModelScope.launch {
            try {
                val response = repository.listTarefa()
                _myTarefaResponse.value = response

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateTarefa(tarefa: Tarefa){
        viewModelScope.launch {
            try{
                repository.updateTarefa(tarefa)
                listTarefa()
            }catch(e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun deleteTarefa(id: Long){
        viewModelScope.launch {
            try {
                repository.deleteTarefa(id)
                listTarefa()

            }catch (e: Exception){
                Log.d("Erro", e.message.toString())

            }
        }
    }
}