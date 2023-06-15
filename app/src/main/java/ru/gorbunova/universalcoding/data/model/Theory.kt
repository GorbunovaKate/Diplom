package ru.gorbunova.universalcoding.data.model

    import androidx.room.ColumnInfo
    import androidx.room.Entity
    import androidx.room.PrimaryKey
    import ru.gorbunova.universalcoding.utils.Constants.Keys.THEORY_TABLE

    @Entity(tableName = THEORY_TABLE)
    data class Theory(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo
        val title: String,
        @ColumnInfo
        val text: String
    )
