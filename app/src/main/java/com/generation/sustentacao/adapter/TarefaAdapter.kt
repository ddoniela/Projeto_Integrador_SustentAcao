package com.generation.sustentacao.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.generation.sustentacao.MainViewModel
import com.generation.sustentacao.databinding.CardLayoutBinding
import com.generation.sustentacao.model.Tarefa

class TarefaAdapter(
    private val taskItemClickListener: TaskItemClickListener,
    private val mainViewModel: MainViewModel,
    val context: Context
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder (val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }


    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefa[position]

        Glide.with(context)
            .load(tarefa.imagem)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(holder.binding.imageEvento)
        holder.binding.textNomedoEvento.text = tarefa.titulo
        holder.binding.textDescricao.text = tarefa.descricao
        holder.binding.textNomedaOng.text = tarefa.autor
        holder.binding.textTema.text = tarefa.tema.descricao
        holder.binding.textData.text = tarefa.dataHora

        holder.itemView.setOnClickListener{
            taskItemClickListener.onTaskClicked(tarefa)
        }

        holder.binding.buttonDeletar.setOnClickListener {
            showAlertDialog(tarefa.id)
        }
    }



    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<Tarefa>){
        listTarefa = list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    private fun showAlertDialog(id: Long){
        AlertDialog.Builder(context)
            .setTitle("Exluir Tarefa")
            .setMessage("Deseja Excluir a Tarefa?")
            .setPositiveButton("Sim"){
                _,_ -> mainViewModel.deleteTarefa(id)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }
}