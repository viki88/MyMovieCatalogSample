package com.vikination.mymoviesapp.ui.trending

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikination.mymoviesapp.network.NetworkHelper
import com.vikination.mymoviesapp.response.TrendingMovieResponse
import com.vikination.mymoviesapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingViewModel :ViewModel(){

    var trendingMovieResponseLiveData = MutableLiveData<TrendingMovieResponse>()

    fun loadDataTrending(mediaType :String, timeWindow :String){
        NetworkHelper().getApiService().getTredingMovies(mediaType, timeWindow, Constants.API_KEY)
            .enqueue(object : Callback<TrendingMovieResponse> {
                override fun onResponse(
                    call: Call<TrendingMovieResponse>,
                    response: Response<TrendingMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val trendingMovieResponse = response.body()
                        // trigger data trending response
                        trendingMovieResponseLiveData.postValue(trendingMovieResponse)

                    } else {
                        Log.i("TAG", "onResponse: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<TrendingMovieResponse>, t: Throwable) {
                    Log.i("TAG", "onFailure: ${t.localizedMessage}")
                }

            })
    }

}