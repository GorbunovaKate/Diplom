package ru.gorbunova.try_diplom.database.repository

import androidx.lifecycle.LiveData
import ru.gorbunova.try_diplom.database.DatabaseRepository
import ru.gorbunova.try_diplom.database.dao.LessonRoomDao
import ru.gorbunova.try_diplom.model.Lesson

class RoomRepository(private val lessonRoomDao: LessonRoomDao) : DatabaseRepository {
    override val readAll: LiveData<List<Lesson>>
        get() = lessonRoomDao.getAllNotes()
}