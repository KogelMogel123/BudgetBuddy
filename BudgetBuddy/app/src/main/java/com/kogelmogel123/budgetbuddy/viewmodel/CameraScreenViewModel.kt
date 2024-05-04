package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.lifecycle.ViewModel
import java.io.File
import java.util.Calendar
import java.util.concurrent.Executors

class CameraScreenViewModel : ViewModel() {
    fun takePhoto(
        imageCapture: ImageCapture,
        outputDir: File,
        onImageCaptured: (Uri) -> Unit,
        onError: (ImageCaptureException) -> Unit,
        context: Context){
        val executor = Executors.newSingleThreadExecutor()
        val file = File(outputDir, "${ Calendar.getInstance().timeInMillis.toString()}.jpg")

        Log.d("Photo", "file: ${file.path}")

        val outputOpts = ImageCapture.OutputFileOptions.Builder(file).build()
        val callback = object : ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                outputFileResults.savedUri?.let { uri ->
                    onImageCaptured(uri)
                    Log.d("Photo", "Saved photo: $uri")

                    // Skanowanie pliku, aby był widoczny w galerii
                    MediaScannerConnection.scanFile(
                        context,
                        arrayOf(file.absolutePath),
                        null
                    ) { path, uri ->
                        Log.d("Photo", "Add photo to gallery: $uri")
                    }
                }
            }
            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }
        }
        imageCapture.takePicture(outputOpts, executor, callback)
    }
}