package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Budget
import kotlinx.coroutines.flow.Flow

interface IBudgetsRepository {
    /**
     * Retrieve all the budgets from the given data source.
     */
    fun getAllBudgets(): LiveData<List<Budget>>

    /**
     * Retrieve an budget from the given data source that matches with the [id].
     */
    fun getBudgetById(id: Int): Flow<Budget>

    /**
     * Insert budget in the data source
     */
    suspend fun insertBudget(budget: Budget)

    /**
     * Delete budget from the data source
     */
    suspend fun deleteBudget(budget: Budget)

    /**
     * Update budget in the data source
     */
    suspend fun updateBudget(budget: Budget)
}