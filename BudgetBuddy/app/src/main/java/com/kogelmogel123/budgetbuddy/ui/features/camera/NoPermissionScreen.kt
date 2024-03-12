package com.kogelmogel123.budgetbuddy.ui.features.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Camera

@Composable
fun NoPermissionScreen(
    onRequestPermission: () -> Unit
) {

    NoPermissionContent(
        onRequestPermission = onRequestPermission
    )
}

@Composable
private fun NoPermissionContent(
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Należy udzielić pozwolenia na korzystanie z kamery w celu korzystania z podstawowych funkcji tej aplikacji.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRequestPermission) {
            Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera")
            Text(text = "Udzielenie pozwolenia")
        }
    }
}

@Preview
@Composable
private fun Preview_NoPermissionContent() {
    NoPermissionContent(
        onRequestPermission = {}
    )
}
