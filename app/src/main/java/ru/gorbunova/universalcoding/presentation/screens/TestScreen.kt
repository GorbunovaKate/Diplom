package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextIndent
import ru.gorbunova.universalcoding.presentation.components.AlertTopNavBar
import ru.gorbunova.universalcoding.presentation.components.Alert_Dialog
import ru.gorbunova.universalcoding.presentation.components.NumbersQuestions
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TestScreen(navController: NavHostController, viewModel: MainViewModel) {

    val tests = viewModel.readAllQuestion().observeAsState(listOf()).value
    val randomQuestions = tests.shuffled().take(10)

    val questions = mutableListOf<Pair<String, Int>>()
    for (test in randomQuestions) {
        val question = test.question
        val correctAnswerIndex = test.correct_answer
        if (question != Constants.Keys.NONE) {
            questions.add(question to correctAnswerIndex)
        }
    }

    var currentQuestionIndex by remember { mutableStateOf(0) }

    val question_1 = randomQuestions.getOrNull(currentQuestionIndex) ?: Test(
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
    val showDialog = remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AlertTopNavBar(showDialog)
        }
    ) {
            Column(
                Modifier
                    .selectableGroup()
                    .padding(vertical = 2.dp, horizontal = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                NumbersQuestions(questions = questions, isAnswerCorrect = isAnswerCorrect, currentQuestionIndex = currentQuestionIndex)
                Text(
                    text = question_1.question,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.ui.text.TextStyle(
                        textIndent = TextIndent(25.sp, 10.sp)
                    ),
                    lineHeight = 30.sp,
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 3.dp)

            ) {
                    radioOptions.forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    ) {
                        RadioButton(
                            selected = selectedOption == text,
                            onClick = { selectedOption = text },
                            //modifier = Modifier.padding(horizontal = 16.dp),
                        )
                        Text(
                            text = text,
                            //modifier = Modifier.padding(start = 16.dp),
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
                        isAnswerCorrect = true
                        //List_Answers.add("green")
                    }
                    else {
                        isAnswerCorrect = false
                        //List_Answers.add("red")
                    }
                    if (currentQuestionIndex == questions.lastIndex) {
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
        }
    }
    if (showDialog.value) {
        Alert_Dialog(showDialog = showDialog, navController = navController)
    }
    BackHandler(enabled = true, onBack = {showDialog.value = true})
}