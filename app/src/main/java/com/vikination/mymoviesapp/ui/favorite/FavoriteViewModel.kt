package com.vikination.mymoviesapp.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vikination.mymoviesapp.room.DbHelper
import com.vikination.mymoviesapp.room.Favorite
import kotlinx.coroutines.runBlocking

class FavoriteViewModel(application: Application) :AndroidViewModel(application){

    var favListLiveData = MutableLiveData<List<Favorite>>()

    private val favoriteDao = DbHelper.getDb(application).getFavDao()

    fun getAllDataFav(){
        runBlocking {
            favListLiveData.postValue(favoriteDao.getAllfavorite())
        }
    }

    fun deleteDataFav(idMovie :Long){
        runBlocking {
            favoriteDao.deleteFavByIdMovie(idMovie)
            favListLiveData.postValue(favoriteDao.getAllfavorite())
        }
    }

}