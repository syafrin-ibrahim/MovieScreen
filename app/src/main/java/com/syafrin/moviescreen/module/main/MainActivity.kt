package com.syafrin.moviescreen.module.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.syafrin.moviescreen.R
import com.syafrin.moviescreen.Type
import com.syafrin.moviescreen.databinding.ActivityMainBinding
import com.syafrin.moviescreen.module.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MovieTypeAdapter.MovieTypeListener, MovieAdapter.MovieListener {
    val movieAdapter by lazy { MovieAdapter() }
    val movieTypeAdapter by lazy { MovieTypeAdapter() }
    lateinit var viewModel: MainViewModel
    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setUpViewModel()
        setUpDataBinding()
        setupObserver()
        setupListener()
        setUpMovieType()
        viewModel.getNowPlayingMovies()


        movieTypeAdapter.setListener(this)
        movieAdapter.setListener(this)
    }

    private fun setUpDataBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity = this
    }

    private fun  setupListener(){
        searchEditText.doAfterTextChanged{
            viewModel.searchMovie(it.toString())
        }
    }
    private fun setUpViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setContext(this)
    }

    private fun setupObserver(){
        viewModel.moviesResultLiveData.observe(this) {
            viewModel.handleMovieListResponse(it)
        }
        viewModel.moviesLiveData.observe(this) {
            movieAdapter.replaceData(it)
        }
        viewModel.errorMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpMovieType(){
        movieTypeAdapter.replaceData(
            listOf(
                Type.NOW_PLAYING,
                Type.UPCOMING,
//                Type.LATEST,
                Type.POPULAR,
                Type.TOP_RATED
            )
        )
    }

    override fun onMovieTypeClicked(id: Int) {
        when(id){
            Type.NOW_PLAYING.id -> viewModel.getNowPlayingMovies()
            Type.UPCOMING.id -> viewModel.getUpComingMovies()
            Type.POPULAR.id -> viewModel.getPopularMovies()
            Type.TOP_RATED.id -> viewModel.getTopRatedMovies()
//            Type.LATEST.id -> viewModel.getLatestMovies()
        }
    }

    override fun onMovieClicked(id: Int) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movieId", id)
        startActivity(intent)
    }



}
