package com.generation.sustentacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.sustentacao.databinding.FragmentListBinding
import com.generation.sustentacao.databinding.FragmentMenuBaseBinding

class MenuBaseFragment : Fragment() {

    private lateinit var binding: FragmentMenuBaseBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBaseBinding.inflate(layoutInflater, container, false)

        binding.buttonEvento.setOnClickListener{
            findNavController().navigate(R.id.action_menuBaseFragment_to_listFragment)
        }

        binding.buttonDoacao.setOnClickListener{
            findNavController().navigate(R.id.action_menuBaseFragment_to_listDoacaoFragment)
        }


        return binding.root
    }


}