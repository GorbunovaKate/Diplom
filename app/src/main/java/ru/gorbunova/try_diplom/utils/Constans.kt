package ru.gorbunova.try_diplom.utils

import ru.gorbunova.try_diplom.database.DatabaseRepository


lateinit var REPOSITORY: DatabaseRepository

object  Constants {
    const val THEORY = "Theory"
    const val TEST = "Test"

    object Keys {
        const val ID = "Id"
        const val NONE = "none"
    }

    object Screens {
        const val THEORY_SCREEN = "theory_screen"
        const val TEST_SCREEN = "test_screen"
        const val LESSON_SCREEN = "lesson_screen"
    }
}

