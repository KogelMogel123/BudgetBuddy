package com.kogelmogel123.budgetbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import java.time.Month

class BudgetViewModel(private val budgetService: IBudgetService) : ViewModel() {
    fun getBudgetById(id: Int): LiveData<Budget> {
        return budgetService.getBudgetById(id)
    }

    fun getBudgetByDate(month: Month, year: Int): LiveData<Budget> {
        return budgetService.getBudgetByDate(month, year)
    }

    suspend fun addBudget(budget: Budget) {
            budgetService.addBudget(budget)
    }
}