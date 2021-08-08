package com.syafrin.moviescreen.module.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syafrin.moviescreen.data.MovieRepository
import com.syafrin.moviescreen.model.DetailMovie
import kotlinx.coroutines.launch

class DetailMovieViewModel : ViewModel() {

    private var repository: MovieRepository? = null

    val detailMovieResultLiveData = MutableLiveData<Result<DetailMovie>>()
    val detailMovieLiveData = MutableLiveData<DetailMovie>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun setContext(context: Context){
        repository = MovieRepository(context)
    }
    fun getDetailMovie(movieId: Int){
        viewModelScope.launch {
                detailMovieResultLiveData.value = repository?.getDetailMovie(movieId)
        }
    }

    fun handleDetailMovietResponse(result: Result<DetailMovie>){
        if(result.isSuccess){
            detailMovieLiveData.value = result.getOrDefault(DetailMovie())

        }else{
            errorMessageLiveData.value = result.exceptionOrNull()?.message.orEmpty()
        }

    }
}