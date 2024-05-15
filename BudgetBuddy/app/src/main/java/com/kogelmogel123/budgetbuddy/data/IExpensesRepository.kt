package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.flow.Flow
import java.util.Date

/**
 * Repository that provides insert, update, delete, and retrieve of [Expense] from a given data source.
 */
interface IExpensesRepository {
    /**
     * Retrieve all the expenses from the given data source.
     */
    fun getAll(): LiveData<List<Expense>>

    /**
     * Retrieves all expenses from the specified data source for dates from to.
     */
    fun getByStartEndDate(startDate: Date, endDate: Date): LiveData<List<Expense>>

    /**
     * Retrieve an expense from the given data source that matches with the [id].
     */
    fun getById(id: Int): Flow<Expense>

    /**
     * Insert expense in the data source
     */
    suspend fun insert(expense: Expense)

    /**
     * Delete expense from the data source
     */
    suspend fun delete(expense: Expense)

    /**
     * Update expense in the data source
     */
    suspend fun update(expense: Expense)
}
