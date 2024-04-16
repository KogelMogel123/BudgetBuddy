package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.R

@Composable
fun NoPermissionScreen(onRequestPermission: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stringResource(id = R.string.camera_permission_denied), style = MaterialTheme.typography.bodyMedium)
        Button(onClick = onRequestPermission) {
            Text(text = stringResource(id = R.string.request_camera_permission),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(12.dp))
        }
    }
}

@Preview
@Composable
fun NoPermissionScreenPreview(){
    NoPermissionScreen(onRequestPermission = {})
}