package com.kogelmogel123.budgetbuddy.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.kogelmogel123.budgetbuddy.data.IUsersRepository
import com.kogelmogel123.budgetbuddy.model.User
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import java.util.UUID

class UserService(private val usersRepository: IUsersRepository) : IUserService {

    init {
        runBlocking {
            initializeUser()
        }
    }

    override suspend fun getMe(): LiveData<User> {

        initializeUser()

        return usersRepository.getMe()
            .catch { e ->
                Log.e("UserService", "Error fetching user", e)
            }
            .asLiveData()
    }

    override suspend fun createUser(name: String) {
        usersRepository.insert(User(0, name))
    }

    private suspend fun initializeUser() {
        val userFlow = usersRepository.getMe()
            .catch { e ->
                Log.e("UserService", "Error fetching user", e)
            }

        val user = userFlow.firstOrNull()
        if (user == null) {
            val newGuid = UUID.randomUUID().toString()
            usersRepository.insert(User(0, newGuid))
        }
    }
}