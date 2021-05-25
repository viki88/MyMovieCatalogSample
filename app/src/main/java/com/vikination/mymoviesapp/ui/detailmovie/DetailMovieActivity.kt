package com.vikination.mymoviesapp.ui.detailmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.vikination.mymoviesapp.R
import com.vikination.mymoviesapp.databinding.ActivityDetailMovieBinding
import com.vikination.mymoviesapp.response.Movie
import com.vikination.mymoviesapp.utils.Constants

class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val MOVIE_KEY = "movie.key"
    }

    lateinit var binding :ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        Log.i("DetailMovieActivity", "onCreate: $movie")

        setupToolbar()
        setDataDetail(movie)
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setDataDetail(movie: Movie?){
        movie?.let {
            val urlImage = Constants.URL_BASE_IMAGE + Constants.IMAGE_SIZE_500 + movie.poster_path
            Glide.with(this).load(urlImage).into(binding.imageDetail)

            binding.releaseDate.text = "Release date :\n${movie.release_date}"
            binding.ratingMovie.text = "Rating : ${movie.vote_average}"
            binding.overviewDesc.text = movie.overview

            supportActionBar?.setTitle(movie.title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}