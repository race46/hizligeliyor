package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFilterBinding
import com.example.myapplication.databinding.FragmentMainPageBinding
import com.example.myapplication.viewmodel.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class filter : BottomSheetDialogFragment() {
    private var _binding : FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ProductViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilterBinding.inflate(inflater,container,false)

        binding.textView13.setOnClickListener{
            //Navigation.findNavController(it).navigate(R.id.action_main_page_to_kategori)
            viewModel.navController.value?.navigate(R.id.action_filter_to_kategori)
        }
        return binding.root

    }


}