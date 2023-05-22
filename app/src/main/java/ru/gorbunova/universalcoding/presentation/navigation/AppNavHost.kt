package ru.gorbunova.universalcoding.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.gorbunova.universalcoding.MainViewModel
import ru.gorbunova.universalcoding.presentation.screens.TestScreen
import ru.gorbunova.universalcoding.presentation.screens.TestStartScreen
import ru.gorbunova.universalcoding.presentation.screens.TestStopScreen
import ru.gorbunova.universalcoding.presentation.screens.TheoryScreen
import ru.gorbunova.universalcoding.presentation.screens.TopicScreen
import ru.gorbunova.universalcoding.utils.Constants.Keys.ID
import ru.gorbunova.universalcoding.utils.Constants.RESULT
import ru.gorbunova.universalcoding.utils.Constants.SUM
import ru.gorbunova.universalcoding.utils.Constants.Screens.TEST_SCREEN
import ru.gorbunova.universalcoding.utils.Constants.Screens.TEST_START_SCREEN
import ru.gorbunova.universalcoding.utils.Constants.Screens.TEST_STOP_SCREEN
import ru.gorbunova.universalcoding.utils.Constants.Screens.THEORY_SCREEN
import ru.gorbunova.universalcoding.utils.Constants.Screens.TOPIC_SCREEN

sealed class NavRoute(val route: String){
    object Theory: NavRoute(THEORY_SCREEN)
    object Test: NavRoute(TEST_SCREEN)
    object Topic: NavRoute(TOPIC_SCREEN)
    object Test_Start: NavRoute(TEST_START_SCREEN)
    object Test_Stop: NavRoute(TEST_STOP_SCREEN)
}

val screens = listOf(
    NavRoute.Theory,
    //NavRoute.Test
    NavRoute.Test_Start
)

@Composable
fun AppNavHost(mViewModel: MainViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Theory.route) {
        composable(NavRoute.Theory.route){TheoryScreen(navController, viewModel = mViewModel)}
        composable(NavRoute.Test.route){TestScreen(navController, viewModel = mViewModel)}
        composable(NavRoute.Test_Start.route){TestStartScreen(navController)}
        //composable(NavRoute.Test_Stop.route){TestStopScreen(navController)}

        composable(NavRoute.Test_Stop.route + "/{${SUM}}") { backStackEntry ->
            TestStopScreen(navController = navController, numCorrect = backStackEntry.arguments?.getString(SUM))}

        composable(NavRoute.Topic.route + "/{${ID}}") { backStackEntry ->
            TopicScreen(navController = navController, viewModel = mViewModel, noteId = backStackEntry.arguments?.getString(ID))
        }
    }
}