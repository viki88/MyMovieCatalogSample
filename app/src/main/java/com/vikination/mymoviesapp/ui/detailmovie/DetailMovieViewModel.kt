package com.vikination.mymoviesapp.ui.detailmovie

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vikination.mymoviesapp.response.Movie
import com.vikination.mymoviesapp.room.DbHelper
import com.vikination.mymoviesapp.room.Favorite
import kotlinx.coroutines.runBlocking

class DetailMovieViewModel(application: Application) :AndroidViewModel(application) {

    var favoriteDao = DbHelper.getDb(application).getFavDao()

    // livedata Favorite
    var favoriteLiveData :MutableLiveData<List<Favorite>> = MutableLiveData()

    fun saveToFavDb(movie: Movie){
        runBlocking {
            favoriteDao.addNewFav(Favorite(
                idMovie = movie.id.toLong(),
                titleMovie = movie.title,
                releaseDate = movie.release_date,
                rating = movie.vote_average.toString(),
                overview = movie.overview,
                posterPath = movie.poster_path
            ))
            loadLatestFavData()
        }
    }

    fun deleteToFavDb(movie: Movie){
        runBlocking {
            favoriteDao.deleteFavByIdMovie(movie.id.toLong())

            loadLatestFavData()
        }
    }

    fun getFavDataByMovieId(idMovie: Long){
        runBlocking {
            val listFavData = favoriteDao.getFavByidMovie(idMovie)
            favoriteLiveData.postValue(listFavData)
        }
    }

    suspend fun loadLatestFavData(){
        val favlistDataDb = favoriteDao.getAllfavorite()
        Log.i("DetailMovieViewModel", "saveToFavDb: $favlistDataDb")

        favoriteLiveData.postValue(favlistDataDb)
    }

}