package com.kogelmogel123.budgetbuddy.data

import com.kogelmogel123.budgetbuddy.model.User
import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: IUserDao) : IUsersRepository {
    override fun getMe(): Flow<User> = userDao.getMe()

    override suspend fun insert(user: User) = userDao.insert(user)
}