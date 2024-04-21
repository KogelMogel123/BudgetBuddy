package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.kogelmogel123.budgetbuddy.ui.screens.camera.CameraPreviewScreen
import com.kogelmogel123.budgetbuddy.ui.screens.camera.CameraScreen
import com.kogelmogel123.budgetbuddy.ui.screens.camera.NoPermissionScreen
import com.kogelmogel123.budgetbuddy.viewmodel.CameraScreenViewModel
//import com.kogelmogel123.budgetbuddy.viewmodel.ScanReceiptViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanReceiptScreen(photoController: LifecycleCameraController, viewModel: CameraScreenViewModel = koinViewModel()) {
    val cameraPermissionState: PermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    CheckCameraPermission(photoController, cameraPermissionState.status.isGranted, onRequestPermission = {
        cameraPermissionState.launchPermissionRequest()
    })
}

//@Preview
//@Composable
//fun ScanReceiptScreenPreview() {
//    ScanReceiptScreen()
//}

@Composable
private fun CheckCameraPermission(photoController: LifecycleCameraController, hasPermission: Boolean, onRequestPermission: () -> Unit){
    if(hasPermission){
        CameraPreviewScreen()
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}