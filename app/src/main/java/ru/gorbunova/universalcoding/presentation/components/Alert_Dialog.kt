package ru.gorbunova.universalcoding.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@Composable
fun Alert_Dialog(showDialog: MutableState<Boolean>, navController: NavHostController) {
    AlertDialog(
    onDismissRequest = { showDialog.value = false },
    title = { Text(text = "Продолжить тест?") },
    text = { Text(text = "Выбранные вами ответы не сохранятся!") },
    confirmButton = {
        TextButton(onClick = { showDialog.value = false }) {
            Text(text = "Продолжить")
        }
    },
    dismissButton = {
        TextButton(onClick = { navController.navigate(NavRoute.Test_Start.route) }) {
            Text(text = "Выйти")
        }
    }
    )
}
