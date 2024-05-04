package com.kogelmogel123.budgetbuddy.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import java.net.URLDecoder

class ReceiptAnalysisScreenViewModel: ViewModel() {
    fun decodeUri(selectedImageEncodedUri: String? = null): Uri?
    {
        var selectedImageUri: Uri? = null

        if(selectedImageEncodedUri != null)
        {
            val selectedImageDecodedUri = URLDecoder.decode(selectedImageEncodedUri, "UTF-8")
            selectedImageUri = Uri.parse(selectedImageDecodedUri)
        }

        return selectedImageUri
    }
}