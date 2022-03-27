package com.example.movies_app.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_app.R
import com.example.movies_app.presentation.model.MovieModel
import com.example.movies_app.presentation.utils.Navigation

class MoviesAdapter(val movies :List<MovieModel>,private val listener:Navigation) :RecyclerView.Adapter<MoviesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    val binding = LayoutInflater.from(parent.context).inflate(R.layout.items_movie,parent,false)
    return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       val items = movies[position]
        holder.bind(items,listener)
    }

    override fun getItemCount(): Int {
    return movies.size
    }
}