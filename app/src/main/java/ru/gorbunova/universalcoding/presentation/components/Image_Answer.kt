package ru.gorbunova.universalcoding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.gorbunova.universalcoding.R

@Composable
fun Image_Answer(numCorrect: String?) {

    val num = numCorrect?.toIntOrNull() ?: 0
    val images = arrayOf(
        Pair(R.drawable.crying, "Crying"),
        Pair(R.drawable.crying, "Crying"),
        Pair(R.drawable.nerd, "Nerd"),
        Pair(R.drawable.puzzled, "Puzzled"),
        Pair(R.drawable.neutral, "Neutral"),
        Pair(R.drawable.confused, "Confused"),
        Pair(R.drawable.happy, "Happy"),
        Pair(R.drawable.lol, "LOL"),
        Pair(R.drawable.cool, "Cool")
    )

    if (num in 0..10) {
        val (imageId, description) = images[num]
        Image(
            painter = painterResource(id = imageId),
            contentDescription = description,
            modifier = Modifier.fillMaxWidth()
        )
    }
}