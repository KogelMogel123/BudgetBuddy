package com.kogelmogel123.budgetbuddy.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ReceiptsImagesScreen() {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            selectedImage = it
        }

    MyContent(selectedImage) {
        launcher.launch("image/*")
    }
}

@Composable
fun MyContent(selectedImage: Uri? = null, onImageClick: () -> Unit) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedImage != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImage),
                    contentDescription = "Receipt Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onImageClick() },
                    contentScale = ContentScale.Fit
                )
                Button(onClick = onImageClick)
                {
                    Text(text = "Send photo for analysis")
                }
                OutlinedButton(onClick = onImageClick)
                {
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                    Text(text = "Select another photo for analysis")
                }
            } else {
                Button(onClick = onImageClick)
                {
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                    Text(text = "Select a photo for analysis")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyContentPreview() {
    var selectedImage: Uri? = null
    MyContent(selectedImage, {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyContentPreview2() {
    var selectedImage: Uri = Uri.parse("")
    MyContent(selectedImage, {})
}