package com.syafrin.moviescreen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.syafrin.moviescreen.model.DetailMovie
import com.syafrin.moviescreen.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: DetailMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)


    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>



    @Query("SELECT * FROM detailmovie WHERE id = :movieId")
    fun getDetailMovie(movieId: Int):DetailMovie
}