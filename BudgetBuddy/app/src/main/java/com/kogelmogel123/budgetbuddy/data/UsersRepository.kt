package com.kogelmogel123.budgetbuddy.data

import kotlinx.coroutines.flow.Flow
import com.kogelmogel123.budgetbuddy.model.User

/**
 * Repository that provides insert, update, delete, and retrieve of [User] from a given data source.
 */
interface UsersRepository {
    /**
     * Retrieve all the users from the given data source.
     */
    //fun getAllUsersStream(): Flow<List<User>>

    /**
     * Retrieve an user from the given data source that matches with the [id].
     */
    //fun getUserStream(id: Int): Flow<User?>

    /**
     * Insert user in the data source
     */
    suspend fun insertUser(user: User)

    /**
     * Delete user from the data source
     */
    suspend fun deleteUser(user: User)

    /**
     * Update user in the data source
     */
    suspend fun updateUser(user: User)
}
