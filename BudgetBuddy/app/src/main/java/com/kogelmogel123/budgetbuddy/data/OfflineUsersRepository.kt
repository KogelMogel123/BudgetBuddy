package com.kogelmogel123.budgetbuddy.data

import com.kogelmogel123.budgetbuddy.dao.UserDao
import com.kogelmogel123.budgetbuddy.model.User
import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: UserDao) : UsersRepository {
    //override fun getAllItemsStream(): Flow<List<User>> = userDao.getAllItems()

    //override fun getItemStream(id: Int): Flow<User?> = userDao.getItem(id)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
}
