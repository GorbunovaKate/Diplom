package ru.gorbunova.try_diplom.database

import androidx.lifecycle.LiveData
import ru.gorbunova.try_diplom.model.Lesson

interface DatabaseRepository {

    val readAll: LiveData<List<Lesson>>
}