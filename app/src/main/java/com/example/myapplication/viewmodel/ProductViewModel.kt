package com.example.myapplication.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.example.myapplication.model.Product
import com.example.myapplication.model.Rating
import com.example.myapplication.service.ApiService
import com.example.myapplication.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProductViewModel: ViewModel() {
    private val service = ApiService()
    private val disposable = CompositeDisposable()

    val navController= MutableLiveData<NavController>()

    val productsList = MutableLiveData<List<Product>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    val selectedCategory = MutableLiveData<ArrayList<String>>(arrayListOf())

    fun loadData(){
        if(productsList.value ==null)
            getFromNet()
    }

    private fun getFromNet(){

        loading.value = true

        disposable.add(
            service.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Product>>(){
                    override fun onSuccess(t: List<Product>) {
                        productsList.value = t
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        error.value = true
                    }

                })

        )
    }

}

