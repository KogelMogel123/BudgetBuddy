package com.kogelmogel123.budgetbuddy.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun ReceiptsImagesScreen() {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        selectedImage = it
    }

    MyContent {
        launcher.launch("image/*")
    }
}

@Composable
fun MyContent(selectedImage: Uri? = null, onImageClick:() -> Unit)
{
    Scaffold(){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(it)
        ){
            if(selectedImage != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImage),
                    contentDescription = "Receipt Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onImageClick() },
                    contentScale = ContentScale.Fit)
            }
            else{
                OutlinedButton(onClick = onImageClick)
                {
                    Text(text = "Choose Image")
                }
            }
        }
    }
}