package com.kogelmogel123.budgetbuddy.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kogelmogel123.budgetbuddy.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    //@Query("SELECT * from users WHERE id = :id")
    //fun getItem(id: Int): Flow<User>

    //@Query("SELECT * from users ORDER BY name ASC")
    //fun getAllItems(): Flow<List<User>>
}
