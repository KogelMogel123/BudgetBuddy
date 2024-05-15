package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.service.ICameraService
import kotlinx.coroutines.CoroutineScope

class CameraViewModel(private val cameraService : ICameraService) : ViewModel() {
    fun captureImage(imageCapture: ImageCapture, context: Context, snackbarHostState: SnackbarHostState, coroutineScope: CoroutineScope, navController: NavController) {
        cameraService.captureImage(imageCapture, context, snackbarHostState, coroutineScope, navController)
    }
}