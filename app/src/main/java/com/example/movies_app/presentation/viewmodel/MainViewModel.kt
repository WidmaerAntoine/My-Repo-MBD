package com.example.movies_app.presentation.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app.domain.repository.Repository
import com.example.movies_app.presentation.model.DetailsMovie
import com.example.movies_app.presentation.model.MovieModel
import com.example.movies_app.presentation.model.ResponseApi
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.ArrayList

class MainViewModel(val repository: Repository) : ViewModel() {
   private var _myResponse = MutableLiveData<Response<ResponseApi>>()
    var liveData: LiveData<Response<ResponseApi>> = _myResponse

    private var _detailMovie = MutableLiveData<Response<DetailsMovie>>()
    val detailsLiveData :LiveData<Response<DetailsMovie>> =_detailMovie


    private var progressBar = MutableLiveData<Boolean>()
    val pBar: LiveData<Boolean> = progressBar


    private val _toolbarVisibility: MutableLiveData<Int> = MutableLiveData()
    val toolbarVisibility: LiveData<Int> = _toolbarVisibility

    fun getAllMOviesList(api_key: String) {
        viewModelScope.launch {
            progressBar.postValue(true)
            val response = repository.getMovierepository(api_key)
            if (response.isSuccessful){
                _myResponse.value = response
                progressBar.postValue(false)
            }else{

                progressBar.postValue(false)
            }

        }
    }

    fun getDetails(id :Int,api_key: String){
        viewModelScope.launch {
            progressBar.postValue(true)
            val responseDetails = repository.getDetailsMovie(id,api_key)
            if (responseDetails.isSuccessful){
                _detailMovie.value = responseDetails
                Log.e("MY_DATA",responseDetails.body().toString())
                progressBar.postValue(false)
            }else{
                Log.e("MY_ERROR",responseDetails.errorBody().toString())
                progressBar.postValue(false)
            }
        }


    }

    }





