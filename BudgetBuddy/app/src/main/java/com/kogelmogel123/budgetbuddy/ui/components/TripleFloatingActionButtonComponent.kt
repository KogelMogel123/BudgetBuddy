package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TripleFloatingActionButton(onFirstButtonClick: () -> Unit, onSecondButtonClick: () -> Unit, onThirdButtonClick: () -> Unit,
                               imageVector: ImageVector, contentDescription: String,
                               imageVector2: ImageVector, contentDescription2: String,
                               imageVector3: ImageVector, contentDescription3: String) {
    Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
        FloatingActionButton(onClick = onFirstButtonClick, modifier = Modifier.padding(end = 8.dp)) {
            Icon(imageVector = imageVector, contentDescription = contentDescription)
        }
        FloatingActionButton(onClick = onSecondButtonClick, modifier = Modifier.padding(end = 8.dp)) {
            Icon(imageVector = imageVector2, contentDescription = contentDescription2)
        }
        FloatingActionButton(onClick = onThirdButtonClick, modifier = Modifier.padding(end = 8.dp)) {
            Icon(imageVector = imageVector3, contentDescription = contentDescription3)
        }
    }
}

@Preview
@Composable
fun TripleFloatingActionButtonPreview() {
    TripleFloatingActionButton(onFirstButtonClick = {}, onSecondButtonClick = {}, onThirdButtonClick = {},
        imageVector = Icons.Filled.Search, contentDescription = "Scan receipt",
        imageVector2 = Icons.Filled.Add, contentDescription2 = "Add",
        imageVector3 = Icons.Filled.Image, contentDescription3 = "Add photo")
}