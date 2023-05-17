package ru.gorbunova.universalcoding.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gorbunova.universalcoding.utils.Constants.Keys.TEST_TABLE

@Entity(tableName = TEST_TABLE)
data class Test(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val question: String,
    @ColumnInfo
    val answer_1: String,
    @ColumnInfo
    val answer_2: String,
    @ColumnInfo
    val answer_3: String,
    @ColumnInfo
    val answer_4: String,
    @ColumnInfo
    val correct_answer: Int
)