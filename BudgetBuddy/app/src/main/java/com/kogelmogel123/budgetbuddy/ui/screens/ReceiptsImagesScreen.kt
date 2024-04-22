package com.kogelmogel123.budgetbuddy.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.kogelmogel123.budgetbuddy.viewmodel.ReceiptsImagesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReceiptsImagesScreen(viewModel: ReceiptsImagesViewModel = koinViewModel()) {
    val imagesUriList by viewModel.imagesUriList.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.loadImagesFromReceiptsFolder(context)
    }

    LazyColumn {
        items(imagesUriList) { imageUri ->
            Image(
                painter = rememberAsyncImagePainter(

                    ImageRequest.Builder(LocalContext.current)
                    .data(data = imageUri)
                    .listener(onError = { request, throwable ->
                        Log.e("ImageLoadError", "Failed to load image {$throwable}")
                        })
                    .apply(block = fun ImageRequest.Builder.() {
                        scale(Scale.FILL)
                    }).build()
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}