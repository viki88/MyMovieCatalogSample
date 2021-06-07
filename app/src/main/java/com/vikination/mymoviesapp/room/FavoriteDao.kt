package com.vikination.mymoviesapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    suspend fun getAllfavorite() :List<Favorite>

    @Insert(onConflict = REPLACE)
    suspend fun addNewFav(favorite: Favorite)

    @Delete
    suspend fun deleteFav(favorite: Favorite)

    @Query("DELETE FROM Favorite WHERE idMovie=:idMovie")
    suspend fun deleteFavByIdMovie(idMovie: Long)

    @Query("SELECT * FROM Favorite WHERE idMovie=:idMovie")
    suspend fun getFavByidMovie(idMovie: Long) :List<Favorite>

}