package com.kogelmogel123.budgetbuddy

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.concurrent.fixedRateTimer

class MainViewModel: ViewModel() {
    private val repo = Repository()
    private val _modelData = MutableStateFlow(0)
    val modelData = _modelData.asStateFlow()

    init{
        makeNetworkRequest()
    }

    private fun makeNetworkRequest() {
        fixedRateTimer(period = 1000L) {
            val randomNumber = repo.fetchData()
            _modelData.value = randomNumber
        }
    }
}