package com.syafrin.moviescreen.data.remote

import com.syafrin.moviescreen.model.DetailMovie
import com.syafrin.moviescreen.model.Movie

class MovieDataSource{

    private val apiEndPoint: ApiEndPoint = ApiRetrofit.getRetrofitInstance().create(
        ApiEndPoint::class.java)

    suspend fun fetchNowPlayingMovies(): Result<List<Movie>> {
        val response = apiEndPoint.fetchNowMovie()

        return if(response.isSuccessful){
             Result.success(response.body()?.results  ?: emptyList())
        }else{
             Result.failure(Throwable(response.message()))
        }
    }

    suspend fun fetchUpcomingMovies(): Result<List<Movie>>{
        val response = apiEndPoint.fetchUpcomingMovie()

        return if(response.isSuccessful){
            Result.success(response.body()?.results  ?: emptyList())
        }else{
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun fetchPopularMovies(): Result<List<Movie>>{
        val response = apiEndPoint.fetchPopularMovie()

        return if(response.isSuccessful){
            Result.success(response.body()?.results  ?: emptyList())
        }else{
            Result.failure(Throwable(response.message()))
        }
    }

//    suspend fun fetchLatestMovies(): Result<List<Movie>>{
//        val response = apiEndPoint.fetchLatestMovie()
//
//        return if(response.isSuccessful){
//            Result.success(response.body()?.results  ?: emptyList())
//        }else{
//            Result.failure(Throwable(response.message()))
//        }
//    }

    suspend fun fetchTopRatedMovies(): Result<List<Movie>>{
        val response = apiEndPoint.fetchTopRatedMovie()

        return if(response.isSuccessful){
            Result.success(response.body()?.results  ?: emptyList())
        }else{
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun searchMovie(query : String): Result<List<Movie>>{
        val response = apiEndPoint.searchMovie(query = query)

         return if(response.isSuccessful){
            Result.success(response.body()?.results  ?: emptyList())
        }else{
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun detailMovie(movieId : Int): Result<DetailMovie>{
        val response = apiEndPoint.fetchDetailMovie(movieId = movieId)

        return if(response.isSuccessful){
            Result.success(response.body() ?: DetailMovie() )
        }else{
            Result.failure(Throwable(response.message()))
        }
    }
}