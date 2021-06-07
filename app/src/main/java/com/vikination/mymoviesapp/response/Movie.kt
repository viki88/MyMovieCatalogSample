package com.vikination.mymoviesapp.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var title :String = "",
    var name :String? = null,
    var adult :Boolean = false,
    var backdrop_path :String = "",
    var id :Int = 0,
    var original_language :String = "",
    var original_title :String = "",
    var poster_path :String = "",
    var video :Boolean = false,
    var vote_average :Double = 0.0,
    var vote_count :Int = 0,
    var overview :String = "",
    var release_date :String = "",
    var genre_ids : List<Int> = listOf(),
    var popularity :Double = 0.0,
    var media_type :String = ""
) :Parcelable