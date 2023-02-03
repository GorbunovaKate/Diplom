package ru.gorbunova.try_diplom.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.gorbunova.try_diplom.MainViewModel
import androidx.compose.runtime.livedata.observeAsState
import ru.gorbunova.try_diplom.components.BottomNavBar
import ru.gorbunova.try_diplom.components.LessonItem
import ru.gorbunova.try_diplom.components.TopLevelScaffold

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TheoryScreen(navController: NavHostController, viewModel: MainViewModel){

    val lessons = viewModel.readAllLessons().observeAsState(listOf()).value

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        LazyColumn {
            items(lessons){ lesson->
                LessonItem(lesson = lesson , navController = navController)
            }
        }
    }
}

//    TopLevelScaffold(
//        navController = navController
//    ) { innerPadding ->
//        Surface(
//            modifier = androidx.compose.ui.Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ) {
//            Text(text = "Theory Screen",
//                modifier = Modifier.padding(start = 8.dp))
//        }
//    }
//}