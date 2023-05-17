package ru.gorbunova.universalcoding.data.database

import androidx.lifecycle.LiveData
import ru.gorbunova.universalcoding.data.model.Test
import ru.gorbunova.universalcoding.data.model.Theory

interface DatabaseRepository {

    val readAll: LiveData<List<Theory>>

    val readAllQuestion: LiveData<List<Test>>
}