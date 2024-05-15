package com.kogelmogel123.budgetbuddy.service

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

interface ICameraService {
    fun captureImage(imageCapture: ImageCapture, context: Context, snackbarHostState: SnackbarHostState, coroutineScope: CoroutineScope, navController: NavController)
}