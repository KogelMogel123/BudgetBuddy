package com.kogelmogel123.budgetbuddy.screens

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.kogelmogel123.budgetbuddy.ui.features.camera.CameraScreen
import com.kogelmogel123.budgetbuddy.ui.features.camera.NoPermissionScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanReceiptScreen(onClick: (String) -> Unit) {

    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    CameraContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest
    )
}

@Composable
private fun CameraContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit
) {

    if (hasPermission) {
        CameraScreen()
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun Preview_CameraContent() {
    CameraContent(
        hasPermission = false,
        onRequestPermission = {}
    )
}