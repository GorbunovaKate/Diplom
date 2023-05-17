package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.MainViewModel
import ru.gorbunova.universalcoding.data.model.Test
import ru.gorbunova.universalcoding.utils.Constants
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestScreen(navController: NavHostController, viewModel: MainViewModel)
{
    val tests = viewModel.readAllQuestion().observeAsState(listOf()).value
    val currentQuestionId = remember { mutableStateOf(0) }
    val question = tests.getOrNull(currentQuestionId.value) ?: Test(
        question = Constants.Keys.NONE,
        answer_1 = Constants.Keys.NONE,
        answer_2 = Constants.Keys.NONE,
        answer_3 = Constants.Keys.NONE,
        answer_4 = Constants.Keys.NONE,
        correct_answer = 0
    )

    Column(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = question.question,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            //textAlign = TextAlign.Justify
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)

        ) {
            Row {
                RadioButton(
                    selected = false,
                    onClick = { TODO() },
                    modifier = Modifier.padding(8.dp)
                )
                Text(modifier = Modifier.padding(8.dp), text = question.answer_1, fontSize = 22.sp)
            }
            Row {
                RadioButton(
                    selected = false,
                    onClick = { TODO() },
                    modifier = Modifier.padding(8.dp)
                )
                Text(modifier = Modifier.padding(8.dp), text = question.answer_2, fontSize = 22.sp)
            }
            Row {
                RadioButton(
                    selected = false,
                    onClick = { TODO() },
                    modifier = Modifier.padding(8.dp)
                )
                Text(modifier = Modifier.padding(8.dp), text = question.answer_3, fontSize = 22.sp)
            }

        }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                if (question.id > 1) {
                    Button(onClick = { currentQuestionId.value-- }) {
                        Text("Предыдущий вопрос")
                    }
                }
                if (question.id < tests.size) {
                    Button(onClick = { currentQuestionId.value++ }) {
                        Text("Следующий вопрос")
                    }
                }
            }
    }
}
