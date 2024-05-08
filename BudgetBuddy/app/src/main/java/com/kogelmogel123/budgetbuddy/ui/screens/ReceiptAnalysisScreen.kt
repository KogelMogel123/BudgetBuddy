package com.kogelmogel123.budgetbuddy.ui.screens

import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.viewmodel.ReceiptAnalysisScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReceiptsAnalysisScreen(viewModel: ReceiptAnalysisScreenViewModel = koinViewModel(), selectedImageEncodedUri: String? = null) {
    val selectedImageUri = viewModel.decodeUri(selectedImageEncodedUri)

    var selectedImage by remember { mutableStateOf<Uri?>(selectedImageUri) }
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
                UploadButton(viewModel = koinViewModel(), selectedImage)
                OutlinedButton(onClick = onImageClick)
                {
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                    Text(text = stringResource(id = R.string.select_another_receipt_for_analysis))
                }
            } else {
                Button(onClick = onImageClick)
                {
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                    Text(text = stringResource(id = R.string.select_receipt_for_analysis))
                }
            }
        }
    }
}

@Composable
fun UploadButton(viewModel: ReceiptAnalysisScreenViewModel, selectedImage: Uri?) {
    val context = LocalContext.current
    Button(
        onClick = {
            viewModel.uploadImage(
                context,
                selectedImage,
                onSuccess = { response -> Log.d("Upload", "Success: $response") },
                onError = { error -> Log.e("Upload", "Error: $error") }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(stringResource(id = R.string.analyze_the_receipt))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyContentPreview() {
    val selectedImage: Uri? = null
    MyContent(selectedImage) {}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyContentPreview2() {
    val selectedImage: Uri = Uri.parse("")
    MyContent(selectedImage) {}
}