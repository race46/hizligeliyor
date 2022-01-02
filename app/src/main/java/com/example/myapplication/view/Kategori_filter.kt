package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityKategoriFilterBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentFilterBinding
import com.example.myapplication.databinding.FragmentKategoriBinding
import com.example.myapplication.viewmodel.ProductViewModel

class Kategori_filter : AppCompatActivity() {
    private lateinit var binding: ActivityKategoriFilterBinding
    private val viewModel : ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriFilterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val x = viewModel.productsList.value?.size
        viewModel.loadData()

        binding.textView5.text = x.toString()
        Toast.makeText(applicationContext, x.toString(),Toast.LENGTH_LONG).show()

        binding.textView5.text = viewModel.productsList.value?.get(0)?.title


    }
}