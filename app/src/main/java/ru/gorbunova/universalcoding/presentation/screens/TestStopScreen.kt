package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import ru.gorbunova.universalcoding.presentation.components.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TestStopScreen (navController: NavHostController, numCorrect: String?) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Вы прошли тест $numCorrect на  из 5",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                //textAlign = TextAlign.Justify
            )
           /* Button(onClick = { navController.navigate(NavRoute.Test.route) }) {
                Text(text = "Да")
            }*/
        }
    }
}