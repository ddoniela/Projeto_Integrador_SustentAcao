package com.generation.sustentacao

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.sustentacao.databinding.FragmentFormBinding
import com.generation.sustentacao.fragment.DatePickerFragment
import com.generation.sustentacao.fragment.TimerPickerListener
import com.generation.sustentacao.model.TarefaEvento
import com.generation.sustentacao.model.Tema
import java.time.LocalDate


class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    var temaSelecionado = 0L
    private var postagemSelecionada: TarefaEvento? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listTemaEvento()

        mainViewModel.dataSelecionada.value = LocalDate.now()

        mainViewModel.myTemaEventoResponse.observe(viewLifecycleOwner){
           response -> Log.d("Requisicao", response.body().toString())
            spinnerTema(response.body())

        }

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner){
            selectedDate -> binding.editTextDate.setText(selectedDate.toString())

        }


        binding.buttonPostar.setOnClickListener{
            inserirNoBanco()

        }

        binding.editTextDate.setOnClickListener{
            DatePickerFragment(this)
                .show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    private fun spinnerTema(listTema: List<Tema>?){
        if (listTema != null){
            binding.spinnerTema.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listTema

                )

            binding.spinnerTema.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selected = binding.spinnerTema.selectedItem as Tema

                        temaSelecionado = selected.id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
        }
    }


    fun validarCampos(
        nome: String, descricao: String, link: String, autor: String
    ): Boolean {

        return !(
                (nome == "" || nome.length < 3 || nome.length > 20) ||
                        (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (link == "" || link.length > 800) ||
                            (autor == "" || autor.length > 50)


                )

    }

    fun inserirNoBanco(){

        val titulo = binding.nomeEventoText.text.toString()
        val descricao = binding.descricaoPng.text.toString()
        val imagem = binding.linkImagem.text.toString()
        val dataHora = binding.editTextDate.text.toString()
        val autor = binding.editTextNomedaOng.text.toString()
        val tema = Tema(temaSelecionado, null, null)

        if (validarCampos(titulo, descricao, imagem, autor)) {

            if(postagemSelecionada == null){
                val tarefa = TarefaEvento(0, titulo, descricao, imagem, dataHora, autor, tema)
                mainViewModel.addTarefaEvento(tarefa)
            }else {
                val tarefa = TarefaEvento(postagemSelecionada?.id!!,
                    titulo, descricao, imagem, dataHora, autor, tema)
                mainViewModel.updateTarefaEvento(tarefa)
            }
            Toast.makeText(context, "Evento salvo", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }


    }
    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemEventoSelecionada
        if(postagemSelecionada != null){
            binding.nomeEventoText.setText(postagemSelecionada?.titulo)
            binding.descricaoPng.setText(postagemSelecionada?.descricao)
            binding.linkImagem.setText(postagemSelecionada?.imagem)
            binding.editTextDate.text = postagemSelecionada?.dataHora
            binding.editTextNomedaOng.setText(postagemSelecionada?.autor)
        } else {
            binding.nomeEventoText.text = null
            binding.descricaoPng.text = null
            binding.linkImagem.text = null
            binding.editTextDate.text = null
            binding.editTextNomedaOng.text = null
        }
    }
    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }


}




