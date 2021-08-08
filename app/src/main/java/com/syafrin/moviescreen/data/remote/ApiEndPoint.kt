package com.syafrin.moviescreen.data.remote

import com.syafrin.moviescreen.model.DetailMovie
import com.syafrin.moviescreen.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @GET("movie/now_playing")
    suspend fun fetchNowMovie(
        @Query("api_key")apiKey : String= ApiKey.api_key
    ): Response<MovieListResponse>


    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovie(
        @Query("api_key")apiKey : String= ApiKey.api_key
    ): Response<MovieListResponse>


    @GET("movie/popular")
    suspend fun fetchPopularMovie(
        @Query("api_key")apiKey : String= ApiKey.api_key
    ): Response<MovieListResponse>

//    @GET("movie/latest")
//    suspend fun fetchLatestMovie(
//        @Query("api_key")apiKey : String= ApiKey.api_key
//    ): Response<MovieListResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovie(
        @Query("api_key")apiKey : String= ApiKey.api_key
    ): Response<MovieListResponse>

    @GET("movie/{movieId}")
    suspend fun fetchDetailMovie(
        @Path("movieId")movieId : Int,
        @Query("api_key")apiKey : String= ApiKey.api_key

    ): Response<DetailMovie>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key")apiKey: String = ApiKey.api_key,
        @Query("query")query:  String
    ): Response<MovieListResponse>



}