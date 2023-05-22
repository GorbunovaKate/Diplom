package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestScreen(navController: NavHostController, viewModel: MainViewModel) {

    val tests = viewModel.readAllQuestion().observeAsState(listOf()).value

    val questions = mutableListOf<Pair<String, Int>>()

    for (test in tests) {
        val question = test.question
        val correctAnswerIndex = test.correct_answer
        if (question != Constants.Keys.NONE) {
            questions.add(question to correctAnswerIndex)
        }
    }

    var currentQuestionIndex by remember { mutableStateOf(0) }

    val question_1 = tests.getOrNull(currentQuestionIndex) ?: Test(
        question = Constants.Keys.NONE,
        answer_1 = Constants.Keys.NONE,
        answer_2 = Constants.Keys.NONE,
        answer_3 = Constants.Keys.NONE,
        answer_4 = Constants.Keys.NONE,
        correct_answer = 0
    )

    val radioOptions = listOf(
        question_1.answer_1,
        question_1.answer_2,
        question_1.answer_3,
        question_1.answer_4
    ).filter { it.isNotEmpty() }

    var selectedOption by remember { mutableStateOf("") }
    var numCorrect by remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }

    Column (Modifier.selectableGroup()
        .padding(vertical = 8.dp, horizontal = 24.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly
    ){
            Text(
                text = question_1.question,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)

        ) {
                radioOptions.forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedOption == text,
                            onClick = { selectedOption = text },
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 16.dp),
                            fontSize = 22.sp
                        )
                    }
                }
            }
            Button(
                onClick = {
                    val correctAnswerIndex = questions[currentQuestionIndex].second
                    val answerIndex = radioOptions.indexOf(selectedOption)
                    if (answerIndex == correctAnswerIndex - 1) {
                        numCorrect++
                    }
                    if (currentQuestionIndex == questions.lastIndex) {
                        // show correct answer message
                        scope.launch {
                            snackbarHostState.value.showSnackbar("$numCorrect out of ${questions.size} answers are correct.")
                        }

                            navController.navigate(NavRoute.Test_Stop.route + "/${numCorrect}")
                    } else {
                        currentQuestionIndex++
                        selectedOption = ""
                    }


                }
            ) {
                if (currentQuestionIndex == questions.lastIndex) {
                    Text(text = "Завершить тест")
                } else {
                    Text(text = "Следующий вопрос")
                }
            }
            SnackbarHost(snackbarHostState.value)
    }
}

/*@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TestScreen(navController: NavHostController, viewModel: MainViewModel) {

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

    val answers = listOf(
        question.answer_1,
        question.answer_2,
        question.answer_3,
        question.answer_4
    ).filter { it.isNotEmpty() }

    var selectedOption by remember { mutableStateOf("") } // initialize with empty string
    var numCorrectAnswers by remember { mutableStateOf(0) }

    Column(Modifier.selectableGroup()
        .padding(vertical = 8.dp, horizontal = 24.dp)
        .fillMaxSize(),
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
            answers.forEach { text ->
                Row {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { selectedOption = text}
                    )
                    Text(text = text, fontSize = 22.sp)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (question.id > 1) {
                Button(onClick = {

                    currentQuestionId.value-- }) {
                    Text("Предыдущий вопрос")
                }
            }
            if (question.id < tests.size) {
                Button(onClick = {
                    if (selectedOption.isNotEmpty()) {
                        if (answers.indexOf(selectedOption) == question.correct_answer - 1) {
                            numCorrectAnswers++
                        }
                        currentQuestionId.value++
                    }
                })
                {
                    Text("Следующий вопрос")
                }
            } else {
                Button(onClick = {
                    //navController.navigate(NavRoute.Test_Stop.route)
                })
                {Text("Завершить тест")}
            }
        }
    }
}*/