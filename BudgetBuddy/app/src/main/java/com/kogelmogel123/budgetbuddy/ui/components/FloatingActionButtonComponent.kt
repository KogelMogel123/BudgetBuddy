package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingActionButtonComponent(onFabClicked: () -> Unit) {
    FloatingActionButton(onClick = onFabClicked) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Preview
@Composable
fun FloatingActionButtonComponentPreview() {
    FloatingActionButtonComponent {}
}