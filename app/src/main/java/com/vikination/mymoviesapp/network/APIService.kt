package com.vikination.mymoviesapp.network

import com.vikination.mymoviesapp.response.TrendingMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("trending/{media_type}/{time_window}")
    fun getTredingMovies(
        @Path("media_type") mediaType :String,
        @Path("time_window") timeWindow :String,
        @Query("api_key") apiKey :String
    ) :Call<TrendingMovieResponse>

}