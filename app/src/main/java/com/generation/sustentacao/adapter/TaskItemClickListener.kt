package com.generation.sustentacao.adapter

import com.generation.sustentacao.model.Tarefa

interface TaskItemClickListener {
    fun onTaskClicked(tarefa: Tarefa)
}