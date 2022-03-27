package com.example.movies_app.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies_app.databinding.ActivityMainBinding
import com.example.movies_app.domain.repository.Repository
import com.example.movies_app.presentation.model.MovieModel
import com.example.movies_app.presentation.utils.Constants.Companion.API_KEY
import com.example.movies_app.presentation.utils.Navigation
import com.example.movies_app.presentation.utils.NavigationScreen
import com.example.movies_app.presentation.view.adapter.MoviesAdapter
import com.example.movies_app.presentation.viewmodel.MainViewModel
import com.example.movies_app.presentation.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() ,Navigation{
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val dataMovie = mutableListOf<MovieModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllMOviesList(API_KEY)
        getAllData()
        initRecycleview()
    }

    private fun initRecycleview() {
        moviesAdapter = MoviesAdapter(dataMovie,this)
        binding.myRecycler.layoutManager = LinearLayoutManager(this)
        binding.myRecycler.adapter = moviesAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllData() {
        viewModel.pBar.observe(this, Observer {
            if (it) binding.progressBar.visibility = View.VISIBLE else View.GONE
            binding.progressBar.isVisible = it
        })
        viewModel.liveData.observe(this, Observer { it ->
            if (it != null) {
                val response = it.body()
                dataMovie.clear()
                response?.result.let { response ->
                    if (response != null) {
                        dataMovie.addAll(response)
                    }
            }
                moviesAdapter.notifyDataSetChanged()
            } else {

                Log.e("Error", it?.errorBody().toString())
            }

        })
    }
    fun navigateToScreen( navigation : NavigationScreen){
        when(navigation){
            NavigationScreen.DETAILS ->{

            }
        }
    }

    override fun navigateToDetails(data: MovieModel) {
        Toast.makeText(this,"HEllo Click ${data.original_title}" ,Toast.LENGTH_LONG).show()
        val intent :Intent = Intent(this,DetailsMovieActivity::class.java)
        intent.putExtra("id",data.id)
        startActivity(intent)

       // navigateToScreen(NavigationScreen.DETAILS)


        //

    }
}
