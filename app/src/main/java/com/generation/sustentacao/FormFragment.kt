package com.generation.sustentacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.generation.sustentacao.databinding.FragmentFormBinding
import com.generation.sustentacao.databinding.FragmentListBinding


class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)


        binding.buttonPostar.setOnClickListener{
            inserirNoBanco()
            //findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        return binding.root
    }

    fun validarCampos(
        nome: String, descricao: String, categoria: String, data: String, hora: String, link: String
    ): Boolean {

        return !(
                (nome == "" || nome.length < 3 || nome.length > 20) ||
                        (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (categoria == "" || categoria.length < 3 || categoria.length > 20) ||
                        (data == "" || data.length < 8 || data.length > 10) ||
                        (hora == "" || hora.length != 5) ||
                        (link == "" || link.length > 200)

                )

    }

    fun inserirNoBanco(){

        val nome = binding.nomeEventoText.text.toString()
        val desc = binding.descricaoPng.text.toString()
        val categoria = binding.categoriaEvento.text.toString()
        val data = binding.editTextDate.text.toString()
        val hora = binding.editTextTime.text.toString()
        val link = binding.linkImagem.text.toString()


        if (validarCampos(nome, desc, categoria, data, hora, link)) {

            Toast.makeText(context, "Tarefa Salva", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }


    }
}




