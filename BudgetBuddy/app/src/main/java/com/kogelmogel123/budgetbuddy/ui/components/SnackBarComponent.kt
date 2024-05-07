package com.kogelmogel123.budgetbuddy.ui.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SnackBarComponent(title: String = "Snackbar", message: String = "Message", imageVector: ImageVector, contentDescription: String = ""){
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(title) },
                icon = { Icon(imageVector, contentDescription) },
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            )
        }
    ) {  contentPadding ->

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SnackBarComponentPreview() {
    SnackBarComponent(title = "Snackbar", message = "Message", imageVector = Icons.Filled.Image , contentDescription = "Image")
}