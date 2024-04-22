package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReceiptsImagesViewModel: ViewModel(){
    // Inicjalizacja MutableStateFlow z pustą listą URI
    private val _imagesUriList = MutableStateFlow<List<Uri>>(emptyList())
    // Publiczny StateFlow tylko do odczytu
    val imagesUriList: StateFlow<List<Uri>> = _imagesUriList

    fun loadImagesFromReceiptsFolder(context: Context) {
        viewModelScope.launch {
            _imagesUriList.value = getImagesFromReceiptsFolder(context)
        }
    }

    fun getImagesFromReceiptsFolder(context: Context): List<Uri> {
        val images = mutableListOf<Uri>()
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} = ?"
        val selectionArgs = arrayOf("Pictures/Receipts/")
        val queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        context.contentResolver.query(
            queryUri,
            projection,
            selection,
            selectionArgs,
            null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "Pictures/Receipts/$id")
                images.add(imageUri)
            }
        }
        return images
    }
}