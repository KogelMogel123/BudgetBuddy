package com.kogelmogel123.budgetbuddy.ui.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.ui.components.MinimalDialogComponent
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController
import com.kogelmogel123.budgetbuddy.viewmodel.ReceiptAnalysisScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReceiptsAnalysisScreen(viewModel: ReceiptAnalysisScreenViewModel = koinViewModel(), navController: NavController,
                           selectedImageEncodedUri: String? = null) {
    val selectedImageUri = viewModel.decodeUri(selectedImageEncodedUri)
    var selectedImage by remember { mutableStateOf<Uri?>(selectedImageUri) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val isLoading = viewModel.isLoading.value

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            selectedImage = it
        }

    if (errorMessage != null) {
        MinimalDialogComponent(dialogText = errorMessage!!, onDismissRequest = { errorMessage = null })
    }

    MyContent(selectedImage, isLoading, navController, {
        launcher.launch("image/*")
    }) {
        errorMessage = it
    }
}

@Composable
fun MyContent(selectedImage: Uri? = null, isLoading: Boolean = false, navController: NavController, onImageClick: () -> Unit, onError: (String) -> Unit) {
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
                UploadButton(viewModel = koinViewModel(), selectedImage, isLoading, onError)
                OutlinedButton(
                    onClick = onImageClick,
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp))
                {
                    Text(text = stringResource(id = R.string.select_another_receipt_for_analysis))
                }
                OutlinedButton(
                    onClick = { navController.navigate("scanReceipt") },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp))
                {
                    Text(text = stringResource(id = R.string.take_a_photo_again))
                }
            } else {
                Button(
                    onClick = onImageClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp))
                {
                    Text(text = stringResource(id = R.string.select_receipt_for_analysis))
                }
            }
        }
    }
}

@Composable
fun UploadButton(viewModel: ReceiptAnalysisScreenViewModel, selectedImage: Uri?, isLoading: Boolean = false, onErrorMessage: (String) -> Unit) {
    val context = LocalContext.current

    Column {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        Button(
            enabled = !isLoading,
            onClick = {
                viewModel.uploadImage(context, selectedImage, onSuccess = { Log.d("Upload", "Success: $it") },
                    onError = { error ->
                        Log.e("Upload", "Error: $error")
                        onErrorMessage(error)
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp)
        ) {
            Text(stringResource(id = R.string.analyze_the_receipt))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyContentPreview() {
    val selectedImage: Uri? = null
    MyContent(selectedImage, false, navController = mockNavController(), {}) {}
}