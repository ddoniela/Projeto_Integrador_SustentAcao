package com.generation.sustentacao

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.generation.sustentacao.databinding.FragmentFormBinding
import com.generation.sustentacao.databinding.FragmentListBinding


class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

<<<<<<< HEAD
        binding.buttonPostar.setOnClickListener{
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
=======
        mainViewModel.listTema()

        mainViewModel.myTemaResponse.observe(viewLifecycleOwner){
            Log.d("Requisicao", it.body().toString())
        }


        binding.buttonPostar.setOnClickListener{
            inserirNoBanco()
            //findNavController().navigate(R.id.action_formFragment_to_listFragment)
>>>>>>> 225d1954c9b1daaee820d1feacfd75b7c3522f13
        }

        return binding.root
    }
<<<<<<< HEAD
}
=======

    fun validarCampos(
        nome: String, descricao: String, categoria: String, data: String, link: String, autor: String
    ): Boolean {

        return !(
                (nome == "" || nome.length < 3 || nome.length > 20) ||
                        (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (categoria == "" || categoria.length < 3 || categoria.length > 20) ||
                        (data == "" || data.length < 8 || data.length > 10) ||
                        (link == "" || link.length > 200)

                )

    }

    fun inserirNoBanco(){

        val nome = binding.nomeEventoText.text.toString()
        val desc = binding.descricaoPng.text.toString()
        val categoria = binding.categoriaEvento.text.toString()
        val data = binding.editTextDate.text.toString()
        val link = binding.linkImagem.text.toString()
        val autor = binding.editTextNomedaOng.text.toString()


        if (validarCampos(nome, desc, categoria, data, link, autor)) {

            Toast.makeText(context, "Tarefa Salva", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }


    }
}




>>>>>>> 225d1954c9b1daaee820d1feacfd75b7c3522f13
