package com.syafrin.moviescreen.module.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.syafrin.moviescreen.R
import com.syafrin.moviescreen.data.remote.ApiEndPoint
import com.syafrin.moviescreen.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {
    private var moveiId = 0
    lateinit var viewModel : DetailMovieViewModel
    lateinit var binding: ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setupBinding()
        processIntent()
        setupViewModel()

        setupObserver()
        viewModel.getDetailMovie(moveiId)

    }

    private fun setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        binding.activity = this

    }
    private fun processIntent(){
        moveiId = intent.getIntExtra("movieId", 0)

    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        viewModel.setContext(this)
    }

    private fun setupObserver() {
        viewModel.detailMovieResultLiveData.observe(this) {
            viewModel.handleDetailMovietResponse(it)
        }
        viewModel.detailMovieLiveData.observe(this) {
            Glide.with(this).load(ApiEndPoint.BASE_IMAGE_URL + it.backDropPath)
                .into(binding.detailMovieImageView)
           binding.movie = it
        }
        viewModel.errorMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    fun onLinkClicked(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


}
