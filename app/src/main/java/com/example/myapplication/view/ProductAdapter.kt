package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ProductItemBinding
import com.example.myapplication.extensionfunctions.loadFromUrl
import com.example.myapplication.extensionfunctions.progresBar
import com.example.myapplication.model.Product
import java.lang.Integer.max

class ProductAdapter(val list: ArrayList<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.productName.text = list[position].title
        holder.binding.imageView.loadFromUrl(list[position].image, progresBar(holder.itemView.context))
        holder.binding.productPrice.text = list[position].price.toString() + " TL"
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updata(newList : List<Product>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}