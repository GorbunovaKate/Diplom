package ru.gorbunova.try_diplom.navigation

import android.provider.SyncStateContract.Constants
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.gorbunova.try_diplom.MainViewModel
import ru.gorbunova.try_diplom.screens.LessonScreen
import ru.gorbunova.try_diplom.screens.TestScreen
import ru.gorbunova.try_diplom.screens.TheoryScreen
import ru.gorbunova.try_diplom.utils.Constants.Screens.LESSON_SCREEN
import ru.gorbunova.try_diplom.utils.Constants.Screens.TEST_SCREEN
import ru.gorbunova.try_diplom.utils.Constants.Screens.THEORY_SCREEN
import ru.gorbunova.try_diplom.utils.Constants.Keys.ID

sealed class NavRoute(val route: String){
    object Theory: NavRoute(THEORY_SCREEN)
    object Test: NavRoute(TEST_SCREEN)
    object Lesson: NavRoute(LESSON_SCREEN)
}

val screens = listOf(
    NavRoute.Theory,
    NavRoute.Test
)

@Composable
fun AppNavHost(mViewModel: MainViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Theory.route) {
        composable(NavRoute.Theory.route){TheoryScreen(navController, viewModel = mViewModel)}
        composable(NavRoute.Test.route){TestScreen(navController)}
        composable(NavRoute.Lesson.route + "/{${ID}}") { backStackEntry ->
            LessonScreen(navController = navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))
        }
    }
}
