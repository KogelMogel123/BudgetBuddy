package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Expense] from a given data source.
 */
interface IExpensesRepository {
    /**
     * Retrieve all the expenses from the given data source.
     */
    fun getAllExpenses(): LiveData<List<Expense>>

    /**
     * Retrieve an expense from the given data source that matches with the [id].
     */
    fun getExpenseById(id: Int): Flow<Expense>

    /**
     * Insert expense in the data source
     */
    suspend fun insertExpense(expense: Expense)

    /**
     * Delete expense from the data source
     */
    suspend fun deleteExpense(expense: Expense)

    /**
     * Update expense in the data source
     */
    suspend fun updateExpense(expense: Expense)
}
