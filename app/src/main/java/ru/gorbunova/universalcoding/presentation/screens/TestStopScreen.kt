package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.presentation.components.Image_Answer
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TestStopScreen (navController: NavHostController, numCorrect: String?) {
    Scaffold(
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Ваш результат - $numCorrect баллов.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Image_Answer(numCorrect = numCorrect)

            Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
                Button(onClick = { navController.navigate(NavRoute.Test_Start.route) }) {
                    Text(text = "Хорошо")
                }

                Button(onClick = { navController.navigate(NavRoute.Test.route) }) {
                    Text(text = "Порйти тест заново")
                }
            }
        }
    }
    BackHandler(enabled = true, onBack = {navController.navigate(NavRoute.Test_Start.route)})
}