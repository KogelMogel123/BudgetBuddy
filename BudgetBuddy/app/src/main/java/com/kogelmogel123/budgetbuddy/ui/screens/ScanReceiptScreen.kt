package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.kogelmogel123.budgetbuddy.ui.screens.camera.CameraPreviewScreen
import com.kogelmogel123.budgetbuddy.ui.screens.camera.NoPermissionScreen
import com.kogelmogel123.budgetbuddy.viewmodel.CameraViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanReceiptScreen(viewModel: CameraViewModel = koinViewModel(), navController: NavController) {
    val cameraPermissionState: PermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    CheckCameraPermission(cameraPermissionState.status.isGranted, viewModel, navController, onRequestPermission = {
        cameraPermissionState.launchPermissionRequest()
    })
}

@Composable
private fun CheckCameraPermission(hasPermission: Boolean, viewModel: CameraViewModel, navController: NavController, onRequestPermission: () -> Unit){
    if(hasPermission){
        CameraPreviewScreen(viewModel, navController)
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}