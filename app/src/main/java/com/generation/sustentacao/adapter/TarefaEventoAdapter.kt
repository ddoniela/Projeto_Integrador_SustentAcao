package com.generation.sustentacao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.sustentacao.ListFragment
import com.generation.sustentacao.MainViewModel
import com.generation.sustentacao.databinding.CardLayoutBinding
import com.generation.sustentacao.model.TarefaEvento

class TarefaEventoAdapter(
    private val taskItemClickListenerEvento: ListFragment,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<TarefaEventoAdapter.TarefaViewHolder>(){

    private var listTarefa = emptyList<TarefaEvento>()

    class TarefaViewHolder (val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefa[position]


        holder.binding.textNomedoEvento.text = tarefa.titulo
        holder.binding.textDescricao.text = tarefa.descricao
        holder.binding.textNomedaOng.text = tarefa.autor
        holder.binding.textTema.text = tarefa.tema.descricao
        holder.binding.textData.text = tarefa.dataHora

        holder.itemView.setOnClickListener{
            taskItemClickListenerEvento.onTaskClickedEvento(tarefa)
        }
    }

    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<TarefaEvento>){
        listTarefa = list.sortedBy { it.id }
        notifyDataSetChanged()
    }
}