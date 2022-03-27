package com.example.movies_app.data

import com.example.movies_app.presentation.model.DetailsMovie
import com.example.movies_app.presentation.model.MovieModel
import com.example.movies_app.presentation.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCall {

     @GET("popular")
     suspend fun getAllMovies(@Query("api_key") api_key:String): Response<ResponseApi>

     @GET("{id_movie}")
     suspend fun getDetailsMovies(@Path("id_movie") id_movie : Int ,
                                  @Query("api_key") api_key: String) : Response<DetailsMovie>
}