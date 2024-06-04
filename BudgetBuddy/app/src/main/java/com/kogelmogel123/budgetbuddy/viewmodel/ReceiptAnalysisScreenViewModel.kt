package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kogelmogel123.budgetbuddy.BuildConfig
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.model.User
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import com.kogelmogel123.budgetbuddy.service.IUserService
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
import java.time.LocalDate
import java.util.Date

class ReceiptAnalysisScreenViewModel(private val expensesRepository: IExpensesRepository, private val userService: IUserService, private val budgetService: IBudgetService): ViewModel() {
    private val client = OkHttpClient()
    var isLoading = mutableStateOf(false)

    val user: LiveData<User> = userService.getMe().also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "User: ${data?.name}")
        }
    }
    private val currentDate = LocalDate.now()

    val budgetForTheCurrentMonth: LiveData<Budget> = budgetService.getBudgetByDate(currentDate.month, currentDate.year).also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "Budget: ${data?.month}")
        }
    }

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

                if(user.value == null)
                {
                    onError("User not found")
                    file?.delete()
                    setLoading(false)
                    return@launch
                }

                val request = Request.Builder()
                    .url(BuildConfig.API_ENDPOINT)
                    .addHeader("ApiKey", BuildConfig.API_KEY)
                    .addHeader("User", user.value?.name ?: "")
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

    private suspend fun getUser(): User? {
        return userService.getMe().value
    }

    fun handleReceiptAnalysisResult(jsonResponse: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val expensesType = object : TypeToken<List<ExpenseData>>() {}.type
                val expensesData: List<ExpenseData> = Gson().fromJson(jsonResponse, expensesType)
                val expenses = expensesData.map { data ->
                    Expense(
                        name = data.name ?: "",
                        cost = data.cost.toDouble() ?: 0.00,
                        category = ExpenseCategory.values().find { it.name.equals(data.category, ignoreCase = true) } ?: ExpenseCategory.OTHER,
                        dateAdded = Date(),
                        budgetId = budgetForTheCurrentMonth.value!!.id
                    )
                }
                expenses.forEach { expense ->
                    expensesRepository.insert(expense)
                }
            } catch (e: Exception) {
                Log.e("ReceiptAnalysis", "Error processing receipt analysis result", e)
            }
        }
    }
    data class ExpenseData(
        val name: String,
        val cost: String,
        val category: String
    )
    private fun decodeUriAndCopyFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputFile = File(context.cacheDir, "upload_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(outputFile)

        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        return outputFile
    }
}