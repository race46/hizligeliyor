package com.example.myapplication.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentKategoriBinding
import com.example.myapplication.viewmodel.ProductViewModel


class kategori : Fragment() {
    val list = ArrayList<String>()
    private var _binding: FragmentKategoriBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKategoriBinding.inflate(inflater, container, false)
        viewModel.selectedCategory.value!!.clear()

        viewModel.productsList.value?.let {
            for (i in viewModel.productsList.value!!) {
                if (!list.contains(i.category)) list.add(i.category)
            }
        }



        val adapter =
            context?.let {
                ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, list)
            }

        binding.listview.adapter = adapter

        binding.listview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                if (viewModel.selectedCategory.value!!.contains(list[position])) {
                    view.setBackgroundColor(Color.WHITE)
                    viewModel.selectedCategory.value!!.remove(list[position])

                } else {
                    view.setBackgroundColor(Color.GREEN)
                    viewModel.selectedCategory.value!!.add(list[position])
                }
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener{
            activity?.onBackPressed()
        }
    }



}
