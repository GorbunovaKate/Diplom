package ru.gorbunova.try_diplom.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.gorbunova.try_diplom.model.Lesson

@Dao
interface LessonRoomDao {
    @Query("SELECT * FROM notes_table")// ПОЛУЧЕНИЕ ВСЕХ ЗАПИСЕЙ
    fun getAllNotes(): LiveData<List<Lesson>>

    @Insert
    fun addNote(note: Lesson)
}
