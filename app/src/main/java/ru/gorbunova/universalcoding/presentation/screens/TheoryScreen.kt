package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.MainViewModel
import ru.gorbunova.universalcoding.presentation.components.BottomNavBar
import ru.gorbunova.universalcoding.presentation.components.ThemeSwitcher
import ru.gorbunova.universalcoding.presentation.components.TopicItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheoryScreen(navController: NavHostController, viewModel: MainViewModel, darkTheme: Boolean, onThemeUpdated: () -> Unit){

    val theory = viewModel.readAllTheory().observeAsState(listOf()).value

    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    ThemeSwitcher(
                        darkTheme = darkTheme,
                        size = 40.dp,
                        padding = 5.dp,
                        onClick = onThemeUpdated
                    )
                },
                title = {
                    Text(text = "Привет")
                }
            )
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        LazyColumn(modifier = androidx.compose.ui.Modifier.padding(vertical = 80.dp)) {
            items(theory){ topic->
                TopicItem(topic = topic, navController = navController)
            }
        }
    }
}