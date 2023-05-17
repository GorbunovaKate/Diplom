package ru.gorbunova.universalcoding.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.gorbunova.universalcoding.data.model.Theory

@Dao
interface TheoryDao {
    @Query("SELECT * FROM THEORY_TABLE")// ПОЛУЧЕНИЕ ВСЕХ ЗАПИСЕЙ
    fun getAllTheory(): LiveData<List<Theory>>
}