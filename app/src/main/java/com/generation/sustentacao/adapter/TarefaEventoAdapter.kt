package com.generation.sustentacao.adapter

import android.R
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.generation.sustentacao.ListFragment
import com.generation.sustentacao.MainViewModel
import com.generation.sustentacao.databinding.CardEventosBinding
import com.generation.sustentacao.model.TarefaEvento

class TarefaEventoAdapter(
    private val taskItemClickListenerEvento: ListFragment,
    private val mainViewModel: MainViewModel,
    private val context: Context
) : RecyclerView.Adapter<TarefaEventoAdapter.TarefaViewHolder>(){
    private var listTarefa = emptyList<TarefaEvento>()

    class TarefaViewHolder (val binding: CardEventosBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardEventosBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefa[position]

        Glide.with(context)
            .load(tarefa.imagem)
            .placeholder(R.drawable.ic_menu_report_image)
            .into(holder.binding.imageEvento)
        holder.binding.textNomedoEvento.text = tarefa.titulo
        holder.binding.textDescricao.text = tarefa.descricao
        holder.binding.textNomedaOng.text = tarefa.autor
        holder.binding.textTema.text = tarefa.tema.descricao
        holder.binding.textData.text = tarefa.dataHora

        holder.itemView.setOnClickListener{
            taskItemClickListenerEvento.onTaskClickedEvento(tarefa)
        }

        holder.binding.buttonDeletarEvento.setOnClickListener {
            showAlertDialogEvento(tarefa.id)
        }
    }



    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<TarefaEvento>){
        listTarefa = list.sortedBy { it.id }
        notifyDataSetChanged()
    }

    private fun showAlertDialogEvento(id: Long){
        AlertDialog.Builder(context)
            .setTitle("Exluir Tarefa")
            .setMessage("Deseja Excluir a Tarefa?")
            .setPositiveButton("Sim"){
                _,_ -> mainViewModel.deleteTarefaEvento(id)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }
}