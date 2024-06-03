package com.kogelmogel123.budgetbuddy.service

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.BudgetWithExpenses
import java.time.Month

interface IBudgetService {
    fun getBudgetById(id: Int): LiveData<Budget>
    fun getBudgetByDate(month: Month, year: Int): LiveData<Budget>
    fun getBudgets(): LiveData<List<Budget>>
    suspend fun createBudget(month: Month, year: Int, amount: Double)
    suspend fun updateBudget(budget: Budget)
    fun getBudgetIdByDate(month: Month, year: Int): Int
    fun getBudgetWithExpenses(): LiveData<List<BudgetWithExpenses>>
}