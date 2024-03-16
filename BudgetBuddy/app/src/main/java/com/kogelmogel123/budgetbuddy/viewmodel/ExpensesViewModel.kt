package com.kogelmogel123.budgetbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.repository.ExpensesRepository

class ExpensesViewModel (private val repo: ExpensesRepository) : ViewModel() {
    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    init {
        // Tu można załadować wydatki, na przykład z repozytorium
        _expenses.value = repo.getExpenses()
    }

    fun sayHello(name : String) : String{
        //val foundUser = repository.findUser(name)
        return "Hello"
    }
}