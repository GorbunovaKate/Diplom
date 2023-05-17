package ru.gorbunova.universalcoding.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.gorbunova.universalcoding.data.database.dao.TestDao
import ru.gorbunova.universalcoding.data.database.dao.TheoryDao
import ru.gorbunova.universalcoding.data.model.Test
import ru.gorbunova.universalcoding.data.model.Theory
import ru.gorbunova.universalcoding.utils.Constants.Keys.APP_DATABASE

@Database(entities = [Theory::class, Test::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun getTheoryDao(): TheoryDao

    abstract fun getTestDao(): TestDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context) : AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    APP_DATABASE
                ).createFromAsset(APP_DATABASE)
                    .build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }
}