package com.syafrin.moviescreen.module.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafrin.moviescreen.R
import com.syafrin.moviescreen.data.remote.ApiEndPoint
import com.syafrin.moviescreen.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val data : MutableList<Movie> = mutableListOf()): RecyclerView.Adapter<MovieAdapter.MovieListHolder>(){

    private var listener : MovieListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        val layoutInvlater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListHolder(layoutInvlater)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    inner class MovieListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item : Movie){
                Glide.with(itemView.context).load(ApiEndPoint.BASE_IMAGE_URL + item.posterPath)
                    .into(itemView.imgMovie)
                itemView.txtMovie.text = item.title
                itemView.txtRating.text = item.voteAverage.toString()
                itemView.setOnClickListener {
                    listener?.onMovieClicked(item.id!!)
                }

        }
    }

    fun replaceData(newData: List<Movie>){
        if(data.isNotEmpty()){
            data.clear()
        }
        data.addAll(newData)
        notifyDataSetChanged()

    }

    fun setListener(newListener: MovieListener){
            this.listener = newListener
    }
    interface MovieListener{
        fun onMovieClicked(id: Int)
    }

}