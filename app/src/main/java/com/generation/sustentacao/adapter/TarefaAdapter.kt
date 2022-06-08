package com.generation.sustentacao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.sustentacao.databinding.CardLayoutBinding
import com.generation.sustentacao.model.Tarefa

class TarefaAdapter : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder (val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefa[position]

        holder.binding.textNomedaOng.text = tarefa.nomeOng
        holder.binding.textNomedoEvento.text = tarefa.nomeEvento
        holder.binding.textDescricao.text = tarefa.descricao
        holder.binding.textEndereco.text = tarefa.endereco
        holder.binding.textHorario.text = tarefa.horario
        holder.binding.textData.text = tarefa.data
    }

    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<Tarefa>){
        listTarefa = list
        notifyDataSetChanged()
    }


}