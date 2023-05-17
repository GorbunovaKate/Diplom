package ru.gorbunova.universalcoding.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.data.model.Theory
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@Composable
fun TopicItem(topic: Theory, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Topic.route  + "/${topic.id}")
            }
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = topic.title,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}