package com.vikination.mymoviesapp.response

data class TrendingMovieResponse(
    var page :Int,
    var results : List<Movie>,
    var total_pages :Int,
    var total_results :Int
)