package com.kogelmogel123.budgetbuddy.ui.features.camera

import android.graphics.Bitmap
import androidx.compose.runtime.Composable

@Composable
fun CameraScreen(

) {
    // ...
}

@Composable
private fun CameraContent(
    onPhotoCaptured: (Bitmap) -> Unit,
    lastCapturedPhoto: Bitmap? = null
) {
    // ...
}
