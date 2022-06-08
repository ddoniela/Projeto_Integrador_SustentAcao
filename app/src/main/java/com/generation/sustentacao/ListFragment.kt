package com.generation.sustentacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.sustentacao.adapter.TarefaAdapter
import com.generation.sustentacao.databinding.FragmentListBinding
import com.generation.sustentacao.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)


        val listTarefas = listOf(
            Tarefa(
                "Conecta Direitos Humanos",
                "Doses de Solidariedade",
                "Evento para unir pessoas e ajudar. Venham fazer parte!",
                "Rua de Paranaguá, 1251",
                "18h",
                "20/12/2022"
            ),

            Tarefa(
                "Conecta Direitos Humanos",
                "Doses de Solidariedade",
                "Evento para unir pessoas e ajudar. Venham fazer parte!",
                "Rua de Paranaguá, 1251",
                "18h",
                "20/12/2022"
            ),

            Tarefa(
                "Conecta Direitos Humanos",
                "Doses de Solidariedade",
                "Evento para unir pessoas e ajudar. Venham fazer parte!",
                "Rua de Paranaguá, 1251",
                "18h",
                "20/12/2022"
            )
        )

        val adapter = TarefaAdapter()
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        adapter.setList(listTarefas)

        binding.floatingAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        return binding.root
    }


}