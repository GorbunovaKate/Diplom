package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.MainViewModel
import ru.gorbunova.universalcoding.presentation.components.BottomNavBar
import ru.gorbunova.universalcoding.presentation.components.TopicItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TheoryScreen(navController: NavHostController, viewModel: MainViewModel){

    val theory = viewModel.readAllTheory().observeAsState(listOf()).value

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        LazyColumn {
            items(theory){ topic->
                TopicItem(topic = topic, navController = navController)
            }
        }
    }
}