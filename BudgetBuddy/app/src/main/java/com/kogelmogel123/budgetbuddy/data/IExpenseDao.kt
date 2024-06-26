package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kogelmogel123.budgetbuddy.model.Expense
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface IExpenseDao {

    @Query("SELECT * FROM expense")
    fun getAll(): LiveData<List<Expense>>

    @Query("SELECT * FROM expense WHERE creation_date BETWEEN :startDate AND :endDate")
    fun getByStartEndDate(startDate: Date, endDate: Date): LiveData<List<Expense>>

    @Query("SELECT * FROM expense WHERE id = :id")
    fun getById(id: Int): Flow<Expense>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)
}