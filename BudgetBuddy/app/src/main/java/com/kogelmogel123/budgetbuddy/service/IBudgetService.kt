package com.kogelmogel123.budgetbuddy.service

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Budget
import java.time.Month

interface IBudgetService {
    fun getBudgetById(id: Int): LiveData<Budget>
    suspend fun addBudget(budget: Budget)
    fun getBudgetByDate(month: Month, year: Int): LiveData<Budget>

}