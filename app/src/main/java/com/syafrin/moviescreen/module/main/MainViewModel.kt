package com.syafrin.moviescreen.module.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafrin.moviescreen.data.MovieRepository
import com.syafrin.moviescreen.model.Movie
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var repository: MovieRepository? = null
    val moviesResultLiveData = MutableLiveData<Result<List<Movie>>>()
    val moviesLiveData = MutableLiveData<List<Movie>>()

    var errorMessageLiveData = MutableLiveData<String>()

    fun setContext(context : Context){
               repository = MovieRepository(context)
      }
    fun getNowPlayingMovies(){
        viewModelScope.launch{
            moviesResultLiveData.value = repository?.fetchNowPlaying()
        }
    }

    fun getUpComingMovies(){
        viewModelScope.launch{
            moviesResultLiveData.value = repository?.fetchUpcoming()
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch{
            moviesResultLiveData.value = repository?.fetchPopular()
        }
    }

//    fun getLatestMovies(){
//        viewModelScope.launch{
//            moviesResultLiveData.value = repository?.fetchLatest()
//        }
//    }

    fun getTopRatedMovies(){
        viewModelScope.launch{
            moviesResultLiveData.value = repository?.fetchTopRated()
        }
    }

    fun searchMovie(query: String){
        viewModelScope.launch {
            if (query.length >= 3){
                moviesResultLiveData.value = repository?.searchMovie(query)
            }

        }
    }

    fun handleMovieListResponse(result: Result<List<Movie>>){
        if(result.isSuccess){
            moviesLiveData.value = result.getOrDefault(emptyList())
        }else{
            errorMessageLiveData.value = result.exceptionOrNull()?.message.orEmpty()
        }

    }
}