package com.kogelmogel123.budgetbuddy.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FloatingActionButtonComponent(onFabClicked: () -> Unit, imageVector: ImageVector, contentDescription: String) {
    FloatingActionButton(onClick = onFabClicked) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Preview
@Composable
fun FloatingActionButtonAddComponentPreview() {
    FloatingActionButtonComponent(onFabClicked = {}, imageVector = Icons.Filled.Add, contentDescription = "Add")
}

@Preview
@Composable
fun FloatingActionButtonScanComponentPreview() {
    FloatingActionButtonComponent(onFabClicked = {}, imageVector = Icons.Filled.Search, contentDescription = "Scan receipt")
}