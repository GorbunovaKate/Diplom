package ru.gorbunova.universalcoding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gorbunova.universalcoding.data.database.AppRoomDatabase
import ru.gorbunova.universalcoding.data.database.repository.RoomRepository
import ru.gorbunova.universalcoding.utils.REPOSITORY
import java.lang.IllegalArgumentException

class MainViewModel (application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase() {
        val theory_dao = AppRoomDatabase.getInstance(context = context).getTheoryDao()
        val test_dao = AppRoomDatabase.getInstance(context = context).getTestDao()
        REPOSITORY = RoomRepository(theory_dao, test_dao)
    }

    fun readAllTheory() = REPOSITORY.readAll
    fun readAllQuestion() = REPOSITORY.readAllQuestion
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}