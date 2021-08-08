package com.syafrin.moviescreen.data.local

import android.content.Context
import com.syafrin.moviescreen.model.DetailMovie
import com.syafrin.moviescreen.model.Movie

class MovieLocalDataSource(ctx : Context) {
    private val dao : MovieDao = MovieDatabase.provideDatabase(ctx).detailMovieDao()
    //private val moviedao : MovieDao = MovieDatabase.provideDatabase(ctx).detailMovieDao()

    fun setMovieDetail(movie : DetailMovie){
            dao.insertMovieDetail(movie)
    }

    fun getMovieDetailById(movieId : Int)= dao.getDetailMovie(movieId)

    fun  insertAll(movies: List<Movie>){
        dao.insertAll(movies)
    }

    fun getAll() = dao.getAll()

}