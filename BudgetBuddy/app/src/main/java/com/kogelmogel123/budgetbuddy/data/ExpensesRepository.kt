package com.kogelmogel123.budgetbuddy.data

import com.kogelmogel123.budgetbuddy.model.Expense

/**
 * Repository that provides insert, update, delete, and retrieve of [Expense] from a given data source.
 */
interface ExpensesRepository {
    /**
     * Retrieve all the expenses from the given data source.
     */
    //fun getAllExpensesStream(): Flow<List<Expense>>

    /**
     * Retrieve an expense from the given data source that matches with the [id].
     */
    //fun getExpenseStream(id: Int): Flow<Expense?>

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
