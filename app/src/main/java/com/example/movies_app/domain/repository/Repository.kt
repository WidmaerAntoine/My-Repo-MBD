package com.example.movies_app.domain.repository

import com.example.movies_app.data.RetrofitInstance
import com.example.movies_app.presentation.model.DetailsMovie
import com.example.movies_app.presentation.model.MovieModel
import com.example.movies_app.presentation.model.ResponseApi
import retrofit2.Response

class Repository {

  suspend  fun getMovierepository(api_key:String) :Response<ResponseApi>{
        return RetrofitInstance.myApi.getAllMovies(api_key)
    }

    suspend fun getDetailsMovie(id_movie :Int,api_key: String) :Response<DetailsMovie>{
        return RetrofitInstance.myApi.getDetailsMovies(id_movie,api_key)
    }
}
