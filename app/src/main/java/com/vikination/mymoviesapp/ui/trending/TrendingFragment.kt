package com.vikination.mymoviesapp.ui.trending

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vikination.mymoviesapp.databinding.FragmentTrendingBinding
import com.vikination.mymoviesapp.response.Movie
import com.vikination.mymoviesapp.ui.detailmovie.DetailMovieActivity
import com.vikination.mymoviesapp.ui.main.MainViewModel
import com.vikination.mymoviesapp.ui.main.MovieListAdapter
import com.vikination.mymoviesapp.ui.main.OnClickItemListMovieListener
import com.vikination.mymoviesapp.utils.Constants

class TrendingFragment :Fragment(), OnClickItemListMovieListener{

    lateinit var binding :FragmentTrendingBinding
    lateinit var viewModel :TrendingViewModel
    lateinit var adapter : MovieListAdapter
    // tambahkan default mediatype dan timewindow
    var selectedMediaType = Constants.MEDIA_TIPE_MOVIE
    var selectedTimeWindow = Constants.TIME_WINDOW_WEEK

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        viewModel.trendingMovieResponseLiveData.observe(this){
            adapter.updateDataMovies(it.results)
        }

        setupRecyclerView()

        viewModel.loadDataTrending(selectedMediaType, selectedTimeWindow)
    }

    private fun setupRecyclerView(){
        adapter = MovieListAdapter(this)
        binding.rvTrendingMovie.adapter = adapter
        binding.rvTrendingMovie.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onClickMovieItem(movie: Movie) {

        val intent = Intent(requireContext(), DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.MOVIE_KEY, movie)
        startActivity(intent)

        // rubah object jadi string
        val json = Gson().toJson(movie)
        Log.i("MainActivity", "movie json: $json")

        // rubah string jadi object
        val movieJson = Gson().fromJson(json, Movie::class.java)
        Log.i("MainActivity", "movie title: ${movieJson.title}")
    }

}