package com.generation.sustentacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.sustentacao.databinding.FragmentPostDoacaoBinding
import com.generation.sustentacao.fragment.DatePickerFragment
import com.generation.sustentacao.fragment.TimerPickerListener
import com.generation.sustentacao.model.TarefaDoacao
import com.generation.sustentacao.model.TarefaEvento
import java.time.LocalDate


class PostDoacaoFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentPostDoacaoBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    var temaSelecionado = 0L
    private var postagemSelecionada: TarefaDoacao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPostDoacaoBinding.inflate(layoutInflater, container, false)

        carregarDados()

        //mainViewModel.listTema()

        mainViewModel.dataSelecionada.value = LocalDate.now()

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner){
                selectedDate -> binding.doacaoData.setText(selectedDate.toString())

        }


        binding.postarDoacao.setOnClickListener{
            inserirNoBanco()

        }

        binding.doacaoData.setOnClickListener{
            DatePickerFragment(this)
                .show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    fun validarCampos(
        autor:String, nome: String, quantidade: String, descricao: String): Boolean {

        return !(
                (nome == "" || nome.length < 3 || nome.length > 20) ||
                        (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (quantidade == "" || quantidade.length > 20)
                )
    }

    fun inserirNoBanco(){

        val autor = binding.doacaoAutor.text.toString()
        val produto = binding.doacaoNome.text.toString()
        val quantidade = binding.doacaoQuantidade.text.toString()
        val descricao = binding.doacaoDescricao.text.toString()
        val entrega = binding.switchDoacao.isChecked
        val data = binding.doacaoData.text.toString()


        if (validarCampos(autor, produto, quantidade, descricao)) {
            if(postagemSelecionada == null){
                val tarefa = TarefaDoacao(0, autor, produto, quantidade, descricao, entrega, data)
                mainViewModel.addTarefaDoacao(tarefa)
            }else {
                val tarefa = TarefaDoacao(postagemSelecionada?.id!!,
                    produto, quantidade, descricao, descricao, entrega, data)
                mainViewModel.updateTarefaDoacao(tarefa)
            }
            Toast.makeText(context, "Doação criada!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_postDoacaoFragment_to_listDoacaoFragment)
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }


    }
    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemDoacaoSelecionada
        if(postagemSelecionada != null){
            binding.doacaoNome.setText(postagemSelecionada?.produto)
            binding.doacaoQuantidade.setText(postagemSelecionada?.quantidade)
            binding.doacaoDescricao.setText(postagemSelecionada?.descricao)
            //binding.doacaoData.text = postagemSelecionada?.data
        } else {
            binding.doacaoNome.text = null
            binding.doacaoQuantidade.text = null
            binding.doacaoDescricao.text = null
            binding.doacaoData.text = null
        }
    }
    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }


}


