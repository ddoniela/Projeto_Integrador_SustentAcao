package com.generation.sustentacao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.sustentacao.ListDoacaoFragment
import com.generation.sustentacao.MainViewModel
import com.generation.sustentacao.databinding.CardDoacaoBinding
import com.generation.sustentacao.model.TarefaDoacao

class TarefaDoacaoAdapter(
    private val taskItemClickListenerDoacao: ListDoacaoFragment,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<TarefaDoacaoAdapter.TarefaDoacaoViewHolder>(){

    private var listTarefa = emptyList<TarefaDoacao>()

    class TarefaDoacaoViewHolder (val binding: CardDoacaoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaDoacaoViewHolder {
        return TarefaDoacaoViewHolder(CardDoacaoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TarefaDoacaoViewHolder, position: Int) {
        val tarefa = listTarefa[position]


        holder.binding.doacaoAutorCard.text = tarefa.autor
        holder.binding.doacaoProdutoCard.text = tarefa.produto
        holder.binding.doacaoEntregaCard.text = tarefa.entrega.toString()
        holder.binding.doacaoDataCard.text = tarefa.data

        holder.itemView.setOnClickListener{
            taskItemClickListenerDoacao.onTaskClickedDoacao(tarefa)
        }
    }

    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setListDoacao(list: List<TarefaDoacao>){
        listTarefa = list.sortedBy { it.id }
        notifyDataSetChanged()
    }

}