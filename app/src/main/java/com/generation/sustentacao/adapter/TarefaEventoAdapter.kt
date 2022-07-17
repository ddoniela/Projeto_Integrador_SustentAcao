package com.generation.sustentacao.adapter

import android.R
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.generation.sustentacao.ListFragment
import com.generation.sustentacao.MainViewModel
import com.generation.sustentacao.databinding.CardEventosBinding
import com.generation.sustentacao.model.TarefaEvento
import java.util.*

class TarefaEventoAdapter(
    private val context: Context,
    private val taskItemClickListenerEvento: ListFragment,
    private val mainViewModel: MainViewModel,

    ) : RecyclerView.Adapter<TarefaEventoAdapter.TarefaViewHolder>(), Filterable {
    private var listTarefa = emptyList<TarefaEvento>()
    // essa variavel serviria como backup pra alocar o filtro dinamicamente sem retirar os elementos
    private var listTarefaFilterable = emptyList<TarefaEvento>()

    class TarefaViewHolder(val binding: CardEventosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(
            CardEventosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
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

        holder.itemView.setOnClickListener {
            taskItemClickListenerEvento.onTaskClickedEvento(tarefa)

        }

        holder.binding.buttonDeletarEvento.setOnClickListener {
            showAlertDialogEvento(tarefa.id)
        }
    }
    /* aq ta toda a lógica de filtro, primeiro ele receba os textos digitais de dentro do list fragment
    dps checa se é nulo, trasformar em string, checa se ta vazio e o lowecase(locale.Root) faz o trabalho
    de tirar o caseSensitive, o .filter{} percorre os dados dos objetos e compara a variavel query que
    recebe o texto digitado pra ver se existe dentro do conteudo dos objetos
     */
    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString().orEmpty().lowercase(Locale.ROOT).trim()
                var filterResults = FilterResults()
                if (query.isEmpty() || query == "" || query == null){
                    filterResults.values = listTarefa
                } else {
                    filterResults.values = listTarefa.filter {
                        it.titulo.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) ||
                                it.autor.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) ||
                                it.descricao.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
                    }
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                listTarefa = filterResults?.values as List<TarefaEvento>
                notifyDataSetChanged()
            }
        }
    }


    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<TarefaEvento>) {
        listTarefa = list.sortedBy { it.id }
        notifyDataSetChanged()
    }

    private fun showAlertDialogEvento(id: Long) {
        AlertDialog.Builder(context)
            .setTitle("Exluir Tarefa")
            .setMessage("Deseja Excluir a Tarefa?")
            .setPositiveButton("Sim") { _, _ ->
                mainViewModel.deleteTarefaEvento(id)
            }
            .setNegativeButton("Não") { _, _ ->
            }.show()
    }
}