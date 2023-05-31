package ru.gorbunova.universalcoding.presentation.components

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import ru.gorbunova.universalcoding.R
import ru.noties.jlatexmath.JLatexMathView

@Composable
fun XML_in(text: String, darkTheme: Boolean) {
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

                        val mathView = it.findViewById<JLatexMathView>(if (darkTheme) R.id.dark_math_view else R.id.math_view)
                        mathView.setLatex(matches[index].value.replace("\\$".toRegex(), ""))
                    }
                )
            }
        }
    }
}
