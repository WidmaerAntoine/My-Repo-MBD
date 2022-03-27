package com.example.movies_app.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movies_app.R
import com.example.movies_app.databinding.ActivityDetailsMovieBinding
import com.example.movies_app.domain.repository.Repository
import com.example.movies_app.presentation.utils.Constants
import com.example.movies_app.presentation.utils.Constants.Companion.API_KEY
import com.example.movies_app.presentation.viewmodel.MainViewModel
import com.example.movies_app.presentation.viewmodel.MainViewModelFactory

class DetailsMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityDetailsMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val mainFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainFactory)[MainViewModel::class.java]
        val bundle = intent.extras

        val id = bundle?.get("id")
        bigData(id as Int?, API_KEY)
    }

    private fun bigData(id: Int?, API_KEY: String) {
        id?.let { viewModel.getDetails(it, API_KEY) }

        viewModel.detailsLiveData.observe(this, Observer { details ->
            if (details?.body() != null) {
                val responseDetails = details.body()
                setUpUi()
                Log.i("MY_RESPONSE", responseDetails.toString())
            }
        })
    }

    private fun setUpUi() {
        with(binding) {
            viewModel.pBar.observe(this@DetailsMovieActivity, Observer { pBar ->
                detailsProgress.isVisible = pBar


            })
        }
    }

}