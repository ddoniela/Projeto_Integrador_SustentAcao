package com.generation.sustentacao.adapter

import com.generation.sustentacao.model.TarefaDoacao
import com.generation.sustentacao.model.TarefaEvento

interface TaskItemClickListenerEvento {
    fun onTaskClickedEvento(tarefa: TarefaEvento)
}

interface TaskItemClickListenerDoacao {
    fun onTaskClickedDoacao(tarefaDoacao: TarefaDoacao)
}