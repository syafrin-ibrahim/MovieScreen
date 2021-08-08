package com.syafrin.moviescreen.data

import android.content.Context
import com.syafrin.moviescreen.data.local.MovieLocalDataSource
import com.syafrin.moviescreen.data.remote.MovieDataSource
import com.syafrin.moviescreen.model.DetailMovie
import com.syafrin.moviescreen.model.Movie

//import com.syafrin.moviescreen.data.remote.MovieDataSource

class MovieRepository(
    private val context : Context,
    private val remoteSource: MovieDataSource = MovieDataSource()
) {
        private val localSource: MovieLocalDataSource = MovieLocalDataSource(context)

       // suspend fun fetchNowPlaying() = remoteSource.fetchNowPlayingMovies()
        suspend fun searchMovie(query: String) = remoteSource.searchMovie(query)
        suspend fun fetchUpcoming() = remoteSource.fetchUpcomingMovies()
        suspend fun fetchPopular() = remoteSource.fetchPopularMovies()
        //suspend fun fetchLatest() = remoteSource.fetchLatestMovies()
        suspend fun fetchTopRated() = remoteSource.fetchTopRatedMovies()
//        suspend fun getDetailMovie(movieId: Int) = remoteSource.detailMovie(movieId)
//        fun getDetailMovieFromDB(movieId: Int) = localSource.getMovieDetailById(movieId)
//        fun saveMovieToDB(movie : DetailMovie) = localSource.setMovieDetail(movie)



        suspend fun getDetailMovie(movieId: Int): Result<DetailMovie> {
            val localData = localSource.getMovieDetailById(movieId)
            return if(localData == null){
                val remoteData = remoteSource.detailMovie(movieId)
                localSource.setMovieDetail(remoteData.getOrDefault(DetailMovie()))
                remoteData
            }else{
                Result.success(localData)
            }


        }
    suspend fun fetchNowPlaying(): Result<List<Movie>>{
        val localData = localSource.getAll()

        return if(localData == null){
            val remoteData = remoteSource.fetchNowPlayingMovies()
            localSource.insertAll(remoteData.getOrDefault(listOf(Movie())))
            remoteData
        }else{
            Result.success(localData)
        }
    }






}