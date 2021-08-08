package com.syafrin.moviescreen.module.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syafrin.moviescreen.R
import com.syafrin.moviescreen.Type
import com.syafrin.moviescreen.model.Movie
import kotlinx.android.synthetic.main.item_type.view.*

class MovieTypeAdapter(
    private val data : MutableList<Type> = mutableListOf()
): RecyclerView.Adapter<MovieTypeAdapter.MovieTypeViewHolder>() {

    private var listener : MovieTypeListener? = null

    inner class MovieTypeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item : Type){
                itemView.tv_type.text = item.value
                itemView.setOnClickListener {
                    listener?.onMovieTypeClicked(item.id)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        return MovieTypeViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MovieTypeViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    fun replaceData(newData: List<Type>){
        if(data.isNotEmpty()){
            data.clear()
        }
        data.addAll(newData)
        notifyDataSetChanged()

    }

    fun setListener(newListener: MovieTypeListener){
            this.listener = newListener
    }

    interface MovieTypeListener{
        fun onMovieTypeClicked(id: Int)
    }
}