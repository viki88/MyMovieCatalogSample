package com.vikination.mymoviesapp.ui.detailmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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
    lateinit var detailMovieViewModel: DetailMovieViewModel
    private var movie :Movie? = null
    private var isFavorited = false
    private var menu :Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra(MOVIE_KEY)
        // instansiasi viewmodel
        detailMovieViewModel = ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
        detailMovieViewModel.favoriteLiveData.observe(this){ favListFromDb ->
            Log.i("TAG", "observe data : $favListFromDb")
            // update fav list akan muncul disini
            val favFromDb = favListFromDb.find { movie?.id?.toLong()?:0 == it.idMovie}
            // check apakah ada data nya di db
            isFavorited = favFromDb != null

            // update menu icon
            updateFavIcon(menu)

        }
        Log.i("DetailMovieActivity", "onCreate: $movie")

        setupToolbar()
        setDataDetail(movie)

        detailMovieViewModel.getFavDataByMovieId(movie?.id?.toLong()?:0)
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
            R.id.menu_favorite -> {
                // simpan data ke fav
                movie?.let {
                    if (isFavorited){ // delete from fav table
                        detailMovieViewModel.deleteToFavDb(it)
                    }else { // save into fav table
                        detailMovieViewModel.saveToFavDb(it)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        // check apakah is favorited
        updateFavIcon(menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    private fun updateFavIcon(menu: Menu?){
        menu?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            if (isFavorited) R.drawable.ic_favorited else R.drawable.ic_favorite)
    }
}