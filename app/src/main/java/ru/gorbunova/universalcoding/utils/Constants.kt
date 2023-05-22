package ru.gorbunova.universalcoding.utils

import ru.gorbunova.universalcoding.data.database.DatabaseRepository

lateinit var REPOSITORY: DatabaseRepository


object  Constants {
    //const val THEORY = "Theory"
    //const val TEST = "Test"
    const val RESULT = "result"
    const val SUM = "numCorrect"

    object Keys {
        const val APP_DATABASE = "app_database.db"
        const val THEORY_TABLE = "theory_table"
        const val TEST_TABLE = "test_table"
        const val ID = "Id"
        const val NONE = "none"
    }

    object Screens {
        const val TOPIC_SCREEN = "topic_screen"
        const val TEST_SCREEN = "test_screen"
        const val THEORY_SCREEN = "theory_screen"
        const val TEST_START_SCREEN = "test_start_screen"
        const val TEST_STOP_SCREEN = "test_stop_screen"
    }
}