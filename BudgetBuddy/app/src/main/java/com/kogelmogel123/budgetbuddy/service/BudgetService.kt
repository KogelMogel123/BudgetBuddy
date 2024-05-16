package com.kogelmogel123.budgetbuddy.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.model.Budget
import kotlinx.coroutines.flow.catch
import java.time.Month

class BudgetService(private val budgetsRepository: IBudgetsRepository): IBudgetService {
    override fun getBudgetById(id: Int): LiveData<Budget> {
        return budgetsRepository.getById(id)
            .catch { e ->
                Log.e("BudgetsViewModel", "Error fetching budget", e)
            }
            .asLiveData()
    }

    override fun getBudgetByDate(month: Month, year: Int): LiveData<Budget> {
        return budgetsRepository.getByDate(month, year)
            .catch { e ->
                Log.e("BudgetsViewModel", "Error fetching budget", e)
            }
            .asLiveData()
    }

    override suspend fun addBudget(budget: Budget) {
        budgetsRepository.insert(budget)
    }
}