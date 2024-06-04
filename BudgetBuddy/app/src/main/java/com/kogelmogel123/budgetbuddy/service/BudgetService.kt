package com.kogelmogel123.budgetbuddy.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.BudgetWithExpenses
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.Month

class BudgetService(private val budgetsRepository: IBudgetsRepository): IBudgetService {
    init {
        runBlocking {
            initializeBudget()
        }
    }

    private suspend fun initializeBudget() {
        val currentDate = LocalDate.now()
        val budgetFlow = budgetsRepository.getByDate(currentDate.month, currentDate.year)
            .catch { e ->
                Log.e("BudgetService", "Error fetching budget", e)
            }

        val budget = budgetFlow.firstOrNull()
        if (budget == null) {
            budgetsRepository.insert(Budget(0, currentDate.month, currentDate.year, 0.0))
        }
    }

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

    override fun getBudgets(): LiveData<List<Budget>> {
        return budgetsRepository.getAll()
    }

    override suspend fun createBudget(month: Month, year: Int, amount: Double) {
        budgetsRepository.insert(Budget(0, month, year, amount))
    }

    override suspend fun updateBudget(budget: Budget) {
        budgetsRepository.update(budget)
    }

    override fun getBudgetIdByDate(month: Month, year: Int): Int {
        return budgetsRepository.getIdByDate(month, year)
    }

    override fun getBudgetWithExpenses(): LiveData<List<BudgetWithExpenses>> {
        return budgetsRepository.getBudgetWithExpenses()
    }
}