package com.kogelmogel123.budgetbuddy.data

import com.kogelmogel123.budgetbuddy.model.User
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    /**
     * Retrieve an user.
     */
    fun getMe(): Flow<User>

    /**
     * Insert user in the data source
     */
    suspend fun insert(user: User)
}