package ru.gorbunova.try_diplom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.gorbunova.try_diplom.database.dao.LessonRoomDao
import ru.gorbunova.try_diplom.model.Lesson

@Database(entities = [Lesson::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase(){
    abstract fun  getRoomDao(): LessonRoomDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context) : AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "notes_database.db"
                ).createFromAsset("notes_database.db")
                    .build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }
}