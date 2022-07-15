package com.generation.sustentacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.sustentacao.api.Repository
import com.generation.sustentacao.model.TarefaDoacao
import com.generation.sustentacao.model.TarefaEvento
import com.generation.sustentacao.model.Tema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var postagemEventoSelecionada: TarefaEvento? = null
    var postagemDoacaoSelecionada: TarefaDoacao? = null

    private val _myTemaEventoResponse =
        MutableLiveData<Response<List<Tema>>>()

    val myTemaEventoResponse: LiveData<Response<List<Tema>>> =
        _myTemaEventoResponse

    private val _myTarefaEventoResponse =
        MutableLiveData<Response<List<TarefaEvento>>>()
    private val _myTarefaDoacaoResponse =
        MutableLiveData<Response<List<TarefaDoacao>>>()

    val myTarefaEventoResponse: LiveData<Response<List<TarefaEvento>>> =
        _myTarefaEventoResponse
    val myTarefaDoacaoResponse: LiveData<Response<List<TarefaDoacao>>> =
        _myTarefaDoacaoResponse

    val dataSelecionada = MutableLiveData<LocalDate>()

    init {

    }

    fun listTemaEvento() {
        viewModelScope.launch {
            try {
                val response = repository.listTemaEvento()
                _myTemaEventoResponse.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addTarefaEvento(tarefa: TarefaEvento) {
        viewModelScope.launch {
            try {
                val response = repository.addTarefaEvento(tarefa)
                Log.d("Opa", response.body().toString())
                listTarefaEvento()

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addTarefaDoacao(tarefaDoacao: TarefaDoacao) {
        viewModelScope.launch {
            try {
                val response = repository.addTarefaDoacao(tarefaDoacao)
                Log.d("Opa", response.body().toString())
                listTarefaDoacao()

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listTarefaEvento() {
        viewModelScope.launch {
            try {
                val response = repository.listTarefaEvento()
                _myTarefaEventoResponse.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listTarefaDoacao() {
        viewModelScope.launch {
            try {
                val response = repository.listTarefaDoacao()
                _myTarefaDoacaoResponse.value = response

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateTarefaEvento(tarefa: TarefaEvento) {
        viewModelScope.launch {
            try {
                repository.updatePostagemEvento(tarefa)
                listTarefaEvento()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateTarefaDoacao(tarefaDoacao: TarefaDoacao) {
        viewModelScope.launch {
            try {
                repository.updatePostagemDoacao(tarefaDoacao)
                listTarefaDoacao()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun deleteTarefaEvento(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteTarefaEvento(id)
                listTarefaEvento()

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())

            }
        }
    }

    fun deleteTarefaDoacao(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteTarefaDoacao(id)
                listTarefaDoacao()

            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())

            }
        }
    }
}