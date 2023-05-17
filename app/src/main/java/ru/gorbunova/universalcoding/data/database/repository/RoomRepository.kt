package ru.gorbunova.universalcoding.data.database.repository

import androidx.lifecycle.LiveData
import ru.gorbunova.universalcoding.data.database.DatabaseRepository
import ru.gorbunova.universalcoding.data.database.dao.TestDao
import ru.gorbunova.universalcoding.data.database.dao.TheoryDao
import ru.gorbunova.universalcoding.data.model.Test
import ru.gorbunova.universalcoding.data.model.Theory

class RoomRepository (private val theoryDao: TheoryDao, private val testDao: TestDao) :
    DatabaseRepository {
    override val readAll: LiveData<List<Theory>>
        get() = theoryDao.getAllTheory()

    override val readAllQuestion: LiveData<List<Test>>
        get() = testDao.getAllQuestions()

}