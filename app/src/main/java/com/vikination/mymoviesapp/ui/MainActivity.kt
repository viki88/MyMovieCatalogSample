package com.vikination.mymoviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikination.mymoviesapp.R
import com.vikination.mymoviesapp.databinding.ActivityMainBinding
import com.vikination.mymoviesapp.network.NetworkHelper
import com.vikination.mymoviesapp.response.Movie
import com.vikination.mymoviesapp.response.TrendingMovieResponse
import com.vikination.mymoviesapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnClickItemListMovieListener {

    lateinit var binding :ActivityMainBinding
    lateinit var adapter :MovieListAdapter

    // tambahkan default mediatype dan timewindow
    var selectedMediaType = Constants.MEDIA_TIPE_MOVIE
    var selectedTimeWindow = Constants.TIME_WINDOW_WEEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setupSpinner()

        setupRecyclerView()

        loadDataTrending(selectedMediaType, selectedTimeWindow)
    }

    private fun setupRecyclerView(){
        adapter = MovieListAdapter(this)
        binding.rvMovie.adapter = adapter
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
    }

    private fun setupSpinner(){

        // setup spinner media type
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(R.array.media_type_strings))
        binding.spinnerMediaType.adapter = adapterSpinner
        binding.spinnerMediaType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedMediaType = parent?.selectedItem as String
                loadDataTrending(selectedMediaType, selectedTimeWindow)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // setup spinner time window
        val adapterSpinnerTimeWindow = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(R.array.time_window_strings))
        binding.spinnerTimewindow.adapter = adapterSpinnerTimeWindow
        binding.spinnerTimewindow.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selectedTimeWindow = parent?.selectedItem as String
                loadDataTrending(selectedMediaType, selectedTimeWindow)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    private fun loadDataTrending(mediaType :String, timeWindow :String){
        NetworkHelper().getApiService().getTredingMovies(mediaType, timeWindow, Constants.API_KEY)
            .enqueue(object : Callback<TrendingMovieResponse> {
                override fun onResponse(
                    call: Call<TrendingMovieResponse>,
                    response: Response<TrendingMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val trendingMovieResponse = response.body()
                        // munculkan semua title movie
                        trendingMovieResponse?.let { adapter.updateDataMovies(it.results) }

                    } else {
                        Log.i("TAG", "onResponse: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<TrendingMovieResponse>, t: Throwable) {
                    Log.i("TAG", "onFailure: ${t.localizedMessage}")
                }

            })
    }

    override fun onClickMovieItem(movie: Movie) {
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()

    }
}