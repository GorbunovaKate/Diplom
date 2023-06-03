package ru.gorbunova.universalcoding.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.West
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigate(NavRoute.Theory.route) }) {
                Icon(
                    imageVector = Icons.Default.West,
                    contentDescription = "Назад"
                )
            }
        },
        title = { Text(text = "") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertTopNavBar(showDialog: MutableState<Boolean>){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {  showDialog.value = true }) {
                Icon(
                    imageVector = Icons.Default.West,
                    contentDescription = "Назад"
                )
            }
        },
        title = {
            Text(text = "")
        }
    )
}