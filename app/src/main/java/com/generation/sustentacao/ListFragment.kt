package com.generation.sustentacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.sustentacao.adapter.TarefaEventoAdapter
import com.generation.sustentacao.adapter.TaskItemClickListenerEvento
import com.generation.sustentacao.databinding.FragmentListBinding
import com.generation.sustentacao.model.TarefaEvento

class ListFragment : Fragment(), TaskItemClickListenerEvento {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel.listTarefaEvento()

        val adapter = TarefaEventoAdapter(requireContext(), this, mainViewModel)

        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        binding.buttonEvento.setOnClickListener {
            Toast.makeText(context, "Você já está na página!", Toast.LENGTH_SHORT).show()
        }

        binding.buttonDoacao.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_listDoacaoFragment)
        }
        mainViewModel.myTarefaEventoResponse.observe(viewLifecycleOwner) { response ->
            if (response.body() != null) {
                adapter.setList(response.body()!!)
            }
        }
        // aq a lógica do searchview, ele capta oq é digitado e joga dentro de um objeto que puxa o filtro
        // de dentro do Adapter TarefaEventoAdapter, o principal é o onQueryTextChange, o onQuery poderia
        // ficar até sem nada
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        return binding.root
    }

    override fun onTaskClickedEvento(tarefa: TarefaEvento) {
        mainViewModel.postagemEventoSelecionada = tarefa
        findNavController().navigate(R.id.action_listFragment_to_formFragment)

    }

}