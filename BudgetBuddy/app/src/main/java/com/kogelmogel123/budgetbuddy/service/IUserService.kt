package com.kogelmogel123.budgetbuddy.service

import androidx.lifecycle.LiveData
import com.kogelmogel123.budgetbuddy.model.User

interface IUserService {
    fun getMe(): LiveData<User>
    suspend fun createUser(name: String)
}