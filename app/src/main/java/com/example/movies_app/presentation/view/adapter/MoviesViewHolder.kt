package com.example.movies_app.presentation.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_app.databinding.ItemsMovieBinding
import com.example.movies_app.presentation.model.MovieModel
import com.example.movies_app.presentation.utils.Constants.Companion.BASE_IMAGE_URL
import com.example.movies_app.presentation.utils.Navigation

class MoviesViewHolder(view :View) :RecyclerView.ViewHolder(view) {
    private val binding = ItemsMovieBinding.bind(view)
    fun bind(model: MovieModel,listener:Navigation){
        with(binding){
            txtTitle.text = model.title
            txtOriginalTitle.text =model.original_title
            txtOverview.text = model.overview
            val imageMovie = BASE_IMAGE_URL + model.poster_path
            Glide.with(binding.root.context).load(imageMovie).into(imageView)

        }
        binding.cardViewGeneral?.setOnClickListener {
            listener.navigateToDetails(model)
        }


    }




}
