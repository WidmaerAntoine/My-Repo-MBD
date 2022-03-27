package com.example.movies_app.presentation.utils

import com.example.movies_app.presentation.model.MovieModel

interface Navigation {
    fun navigateToDetails(data : MovieModel)
}