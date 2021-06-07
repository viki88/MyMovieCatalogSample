package com.vikination.mymoviesapp.ui.favorite

import com.vikination.mymoviesapp.room.Favorite

interface OnClickItemFavListListener {
    fun deleteFav(favorite: Favorite)
    fun onClick(favorite: Favorite)
}