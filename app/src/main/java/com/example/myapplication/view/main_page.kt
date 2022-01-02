package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainPageBinding
import com.example.myapplication.model.Product
import com.example.myapplication.viewmodel.ProductViewModel


class main_page : Fragment(R.layout.fragment_main_page) {

    private val viewModel : ProductViewModel by activityViewModels()
    private var _binding : FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private val adapter = ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainPageBinding.inflate(inflater,container,false)

        binding.filterButton.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_main_page_to_filter)
        }
//        binding.button3.setOnClickListener{
//            Navigation.findNavController(it).navigate(R.id.action_main_page_to_kategori)
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        observeData()
        viewModel.loadData()


        binding.recyclerviewProduct.layoutManager = GridLayoutManager(context,2)//LinearLayoutManager(context)
        binding.recyclerviewProduct.adapter = adapter




    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun observeData(){
        viewModel.productsList.observe(this, Observer {//this
            it?.let {
                adapter.updata(it)
                binding.errorMesage.visibility = View.INVISIBLE
                binding.recyclerviewProduct.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
        viewModel.error.observe(this,{
            it?.let {
                if(it){
                binding.errorMesage.visibility = View.VISIBLE
                binding.recyclerviewProduct.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.INVISIBLE}
            }

        })

        viewModel.selectedCategory.observe(this,{
            if(viewModel.selectedCategory.value!!.size==0){
                viewModel.productsList.value?.let { it1 -> adapter.updata(it1) }
            }else{
                val l = kotlin.collections.arrayListOf<com.example.myapplication.model.Product>()
                for(i in viewModel.productsList.value!!){
                    if(viewModel.selectedCategory.value?.contains(i.category)!!){
                        l.add(i)
                    }
                }
                adapter.updata(l)
            }
        })

        viewModel.loading.observe(this,{
            viewModel.loading.let {
                if(it.value!!){
                    binding.errorMesage.visibility = View.INVISIBLE
                    binding.recyclerviewProduct.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE}
            }
        })

    }

}