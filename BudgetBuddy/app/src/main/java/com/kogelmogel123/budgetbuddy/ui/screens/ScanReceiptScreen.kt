package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.kogelmogel123.budgetbuddy.ui.screens.camera.CameraPreviewScreen
import com.kogelmogel123.budgetbuddy.ui.screens.camera.NoPermissionScreen
import com.kogelmogel123.budgetbuddy.viewmodel.CameraScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanReceiptScreen(viewModel: CameraScreenViewModel = koinViewModel()) {
    val cameraPermissionState: PermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    CheckCameraPermission(cameraPermissionState.status.isGranted, onRequestPermission = {
        cameraPermissionState.launchPermissionRequest()
    })
}

@Composable
private fun CheckCameraPermission(hasPermission: Boolean, onRequestPermission: () -> Unit){
    if(hasPermission){
        CameraPreviewScreen()
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}