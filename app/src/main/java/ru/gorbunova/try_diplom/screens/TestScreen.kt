package ru.gorbunova.try_diplom.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ru.gorbunova.try_diplom.components.TopLevelScaffold

@Composable
fun TestScreen(navController: NavHostController){

    TopLevelScaffold(
        navController = navController
    ) { innerPadding ->
        Surface(
            modifier = androidx.compose.ui.Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Test Screen",
                modifier = Modifier.padding(start = 8.dp))
        }
    }
}