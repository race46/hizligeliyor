package com.example.myapplication.service

import com.example.myapplication.model.Product
import io.reactivex.Single
import retrofit2.http.GET

interface ProductApi {
    //https://fakestoreapi.com/products
    @GET("products")
    fun getProducts(): Single<List<Product>>

}