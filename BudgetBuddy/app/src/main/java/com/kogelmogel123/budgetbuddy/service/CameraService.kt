package com.kogelmogel123.budgetbuddy.service

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.material3.SnackbarHostState
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class CameraService : ICameraService {
    override fun captureImage(imageCapture: ImageCapture, context: Context, snackbarHostState: SnackbarHostState, coroutineScope: CoroutineScope, navController: NavController) {
        val timeStamp = System.currentTimeMillis()
        val name = "Receipt_$timeStamp.jpeg"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Receipts")
        }
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    coroutineScope.launch {
                        val savedUri = outputFileResults.savedUri.toString()
                        val encodedUri = URLEncoder.encode(savedUri, StandardCharsets.UTF_8.toString())
                        navController.navigate("receiptsAnalysisScreen/${encodedUri}")
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    coroutineScope.launch {
                        Log.d("Photo", "Error: ${exception.cause}")
                        snackbarHostState.showSnackbar("Error capturing image")
                    }
                }

            })
    }
}