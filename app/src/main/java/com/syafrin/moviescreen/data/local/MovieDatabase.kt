package com.syafrin.moviescreen.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syafrin.moviescreen.model.DetailMovie
import com.syafrin.moviescreen.model.Movie

//import com.syafrin.moviescreen.model.Movie

@Database(entities = [DetailMovie::class, Movie::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract  fun detailMovieDao(): MovieDao

    companion object{
        fun provideDatabase(ctx : Context): MovieDatabase{
            return Room.databaseBuilder(ctx, MovieDatabase::class.java, "movieDB")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}