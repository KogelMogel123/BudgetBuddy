package com.kogelmogel123.budgetbuddy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kogelmogel123.budgetbuddy.model.Budget
import kotlinx.coroutines.flow.Flow
import java.time.Month

@Dao
interface IBudgetDao {
    @Query("SELECT * FROM budget")
    fun getAll(): LiveData<List<Budget>>

    @Query("SELECT * FROM budget WHERE id = :id")
    fun getById(id: Int): Flow<Budget>

    @Query("SELECT * FROM budget WHERE month = :month AND year = :year")
    fun getByDate(month: Month, year: Int): Flow<Budget>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(budget: Budget)

    @Update
    suspend fun update(budget: Budget)

    @Delete
    suspend fun delete(budget: Budget)
}