package com.generation.sustentacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.sustentacao.adapter.TarefaDoacaoAdapter
import com.generation.sustentacao.adapter.TarefaEventoAdapter
import com.generation.sustentacao.adapter.TaskItemClickListenerDoacao
import com.generation.sustentacao.adapter.TaskItemClickListenerEvento
import com.generation.sustentacao.databinding.FragmentListBinding
import com.generation.sustentacao.databinding.FragmentListDoacaoBinding
import com.generation.sustentacao.model.TarefaDoacao
import com.generation.sustentacao.model.TarefaEvento

class ListDoacaoFragment : Fragment(), TaskItemClickListenerDoacao {

    private lateinit var binding: FragmentListDoacaoBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListDoacaoBinding.inflate(layoutInflater, container, false)

        mainViewModel.listTarefaDoacao()

        val adapter = TarefaDoacaoAdapter(this,mainViewModel)
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)


        binding.floatingAddDoacao.setOnClickListener{
            mainViewModel.postagemDoacaoSelecionada = null
            findNavController().navigate(R.id.action_listDoacaoFragment_to_postDoacaoFragment)
        }

        mainViewModel.myTarefaDoacaoResponse.observe(viewLifecycleOwner){
                response -> if (response.body() != null){
            adapter.setListDoacao(response.body()!!)
        }
        }

        return binding.root
    }

    override fun onTaskClickedDoacao(tarefaDoacao: TarefaDoacao) {
        mainViewModel.postagemDoacaoSelecionada = tarefaDoacao
        findNavController().navigate(R.id.action_listDoacaoFragment_to_postDoacaoFragment)
    }

}