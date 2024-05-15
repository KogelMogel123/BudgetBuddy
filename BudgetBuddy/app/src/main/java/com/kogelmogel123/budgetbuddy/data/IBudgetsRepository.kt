package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.MonthEnum
import kotlinx.coroutines.flow.Flow

interface IBudgetsRepository {
    /**
     * Retrieve all the budgets from the given data source.
     */
    fun getAll(): LiveData<List<Budget>>

    /**
     * Retrieve an budget from the given data source that matches with the [id].
     */
    fun getById(id: Int): Flow<Budget>

    /**
     * Retrieve an budget from the given data source that matches with the [date].
     */
    fun getByDate(month: MonthEnum, year: Int): Flow<Budget>

    /**
     * Insert budget in the data source
     */
    suspend fun insert(budget: Budget)

    /**
     * Delete budget from the data source
     */
    suspend fun delete(budget: Budget)

    /**
     * Update budget in the data source
     */
    suspend fun update(budget: Budget)
}