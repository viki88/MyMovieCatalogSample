package com.vikination.mymoviesapp.room

import android.content.Context
import androidx.room.Room

object DbHelper {
    fun getDb(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "MovieCatalogDb")
        .addMigrations(AppDatabase.MOVIE_1_2_MIGRATION)
        .fallbackToDestructiveMigration()
        .build()
}