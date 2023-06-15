package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.R
import ru.gorbunova.universalcoding.presentation.components.BottomNavBar
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TestStartScreen (navController: NavHostController) {
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
                text = "Хотите проверить свои знания?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                //textAlign = TextAlign.Justify
            )
            Image(
                painter = painterResource(id = R.drawable.question),
                contentDescription = "Question",
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { navController.navigate(NavRoute.Test.route) }) {
                Text(text = "Да")
            }
        }
    }
}