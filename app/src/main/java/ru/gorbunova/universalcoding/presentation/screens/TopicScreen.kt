package ru.gorbunova.universalcoding.presentation.screens

import android.annotation.SuppressLint
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.West
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import ru.gorbunova.universalcoding.MainViewModel
import ru.gorbunova.universalcoding.data.model.Theory
import ru.gorbunova.universalcoding.utils.Constants
import ru.gorbunova.universalcoding.R
import ru.noties.jlatexmath.JLatexMathView

@Composable
fun XML_in(text: String) {
    val regex = Regex("\\$+.*?\\$+")
    val matches = regex.findAll(text).toList()
    val parts = regex.split(text)
    Column(modifier = Modifier.fillMaxWidth()) {
        parts.forEachIndexed { index, part ->
            if (part.isNotEmpty()) {
                Text(
                    text = part,
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp
                )
            }
            if (index < matches.size) {
                AndroidView(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    factory = { View.inflate(it, R.layout.text_layout, null) },
                    update = {
                        val mathView = it.findViewById<JLatexMathView>(R.id.math_view)
                        mathView.setLatex(matches[index].value.replace("\\$".toRegex(), ""))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {

    val lessons = viewModel.readAllTheory().observeAsState(listOf()).value
    /*val lesson = lessons.firstOrNull { it.id == noteId?.toInt() } ?: Theory(
        title = Constants.Keys.NONE,
        text = Constants.Keys.NONE
    )*/
    var currentTopicIndex by remember { mutableStateOf(0) }

    val lesson = lessons.getOrNull(currentTopicIndex) ?: Theory(
        title = Constants.Keys.NONE,
        text = Constants.Keys.NONE
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
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
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = lesson.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp)
            )

            XML_in(lesson.text)

            Row {
                if (currentTopicIndex != 0) {
                    Button(onClick = { currentTopicIndex-- }) {
                        Text(text = "Предыдущая тема")
                    }
                }
                if (currentTopicIndex != lessons.lastIndex) {
                    Button(onClick = { currentTopicIndex++ }) {
                        Text(text = "Следующая тема")
                    }
                }


                /*Row {
                    if (currentTopicIndex == lessons.lastIndex)
                    {
                        Button(onClick = { currentTopicIndex-- }) {

                            Text(text = "Предыдущая тема")
                        }
                        Button(onClick = { currentTopicIndex++ }) {

                            Text(text = "Следующая тема")
                        }
                    }*/
            }
        }
    }
}