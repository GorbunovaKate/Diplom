package ru.gorbunova.universalcoding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumbersQuestions(
    questions: MutableList<Pair<String, Int>>,
    isAnswerCorrect: Boolean,
    currentQuestionIndex: Int,
) {
    val List_Answers = remember { mutableListOf<String>() }
    if (currentQuestionIndex == 1) {
        if (List_Answers.isNotEmpty()) {
            List_Answers.clear()
        }
    }
    List_Answers.add(if (isAnswerCorrect) "green" else "red")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 1..questions.size) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        when (i) {
                            currentQuestionIndex -> if (List_Answers[i - 1] == "green") Color.Green else Color.Red
                            in 1..currentQuestionIndex -> if (List_Answers[i - 1] == "green") Color.Green else Color.Red
                            else -> Color.White
                        },
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = i.toString(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}