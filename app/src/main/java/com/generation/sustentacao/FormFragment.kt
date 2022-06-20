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
import com.generation.sustentacao.model.Tarefa
import com.generation.sustentacao.model.Tema
import java.time.LocalDate


class FormFragment : Fragment(), TimerPickerListener {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    var temaSelecionado = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        mainViewModel.listTema()

        mainViewModel.dataSelecionada.value = LocalDate.now()

        mainViewModel.myTemaResponse.observe(viewLifecycleOwner){
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
        titulo: String, descricao: String, imagem: String, autor: String
    ): Boolean {

        return !(
                (titulo == "" || titulo.length < 3 || titulo.length > 20) ||
                        (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (imagem == "" || imagem.length > 200) ||
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


        if (validarCampos(titulo, descricao, imagem, dataHora)) {
            val tarefa = Tarefa(0, titulo, descricao, imagem, dataHora, autor, tema)
            mainViewModel.addTarefa(tarefa)

            Toast.makeText(context, "Tarefa Salva", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }


}




