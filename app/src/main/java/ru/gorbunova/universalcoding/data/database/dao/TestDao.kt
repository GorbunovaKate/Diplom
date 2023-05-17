package ru.gorbunova.universalcoding.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.gorbunova.universalcoding.data.model.Test

@Dao
interface TestDao {
    @Query("SELECT * FROM TEST_TABLE") //ORDER BY question_id ASC
    fun getAllQuestions(): LiveData<List<Test>>
}