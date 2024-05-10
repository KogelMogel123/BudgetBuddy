package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kogelmogel123.budgetbuddy.BuildConfig
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okio.IOException
import java.io.File
import java.io.FileOutputStream
import java.net.URLDecoder

class ReceiptAnalysisScreenViewModel: ViewModel() {
    private val client = OkHttpClient()
    var isLoading = mutableStateOf(false)

    private fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

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

    fun decodeUriAndCopyFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputFile = File(context.cacheDir, "upload_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(outputFile)

        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        return outputFile
    }

    fun uploadImage(context: Context, uri: Uri?, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        if (uri == null) {
            onError("No image selected")
            return
        }

        setLoading(true)

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Upload", "uri: $uri")

            val file: File = try {
                decodeUriAndCopyFile(context, uri)
            } catch (e: IOException) {
                onError("Cannot process image: ${e.message}")
                return@launch
            }

            try {
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(
                        "file",
                        file.name,
                        file.asRequestBody("application/octet-stream".toMediaTypeOrNull())
                    )
                    .build()

                val request = Request.Builder()
                    .url(BuildConfig.TEST_API_ENDPOINT)
                    .post(requestBody)
                    .build()

                client.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        onSuccess(response.body?.string() ?: "No response from server")
                    } else {
                        onError("File analysis error: ${response.message}")
                    }
                }
            }
            catch (e: IOException) {
                onError("Cannot process image: ${e.message}")
                return@launch
            }
            finally {
                file?.delete()
                setLoading(false)
            }
        }
    }
}