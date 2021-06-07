package com.vikination.mymoviesapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Favorite::class], version = 2)
abstract class AppDatabase :RoomDatabase(){
    abstract fun getFavDao() :FavoriteDao

    companion object{
        val MOVIE_1_2_MIGRATION :Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "DROP TABLE Favorite"
                )
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS Favorite(idMovie INTEGER NOT NULL, title TEXT NOT NULL, releaseDate TEXT NOT NULL, overview TEXT NOT NULL, rating TEXT NOT NULL, posterPath TEXT NOT NULL, PRIMARY KEY(idMovie))"
                )
            }

        }
    }
}
