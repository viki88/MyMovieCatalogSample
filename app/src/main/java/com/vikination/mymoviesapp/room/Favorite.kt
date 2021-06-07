package com.vikination.mymoviesapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey @ColumnInfo(name = "idMovie") var idMovie :Long,
    @ColumnInfo(name = "title") var titleMovie :String,
    @ColumnInfo(name = "releaseDate") var releaseDate :String,
    @ColumnInfo(name = "overview") var overview :String,
    @ColumnInfo(name = "rating") var rating :String,
    @ColumnInfo(name = "posterPath") var posterPath :String
)