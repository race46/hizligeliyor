package com.example.myapplication.extensionfunctions

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R

fun ImageView.loadFromUrl(url:String?,progressDrawable: CircularProgressDrawable){

    val options = RequestOptions
        .placeholderOf(progressDrawable)
        .error(R.drawable.abc_vector_test)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun progresBar(context:Context): CircularProgressDrawable{

    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}