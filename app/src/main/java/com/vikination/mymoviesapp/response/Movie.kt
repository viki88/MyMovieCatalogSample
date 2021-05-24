package com.vikination.mymoviesapp.response

data class Movie(
    var title :String,
    var name :String,
    var adult :Boolean,
    var backdrop_path :String,
    var id :Int,
    var original_language :String,
    var original_title :String,
    var poster_path :String,
    var video :Boolean,
    var vote_average :Double,
    var vote_count :Int,
    var overview :String,
    var release_date :String,
    var genre_ids : List<Int>,
    var popularity :Double,
    var media_type :String
)