package com.kogelmogel123.budgetbuddy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.BudgetWithExpenses
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import com.kogelmogel123.budgetbuddy.service.IExpenseService
import kotlinx.coroutines.launch
import java.time.LocalDate

class BudgetViewModel(private val budgetService: IBudgetService, private val expenseService: IExpenseService) : ViewModel() {
    val budgetsWithExpenses: LiveData<List<BudgetWithExpenses>> = budgetService.getBudgetsWithExpenses()
    val currentDate = LocalDate.now()
    val budget = budgetService.getBudgetByDate(currentDate.month, currentDate.year)

    fun getTotalCost(expenses: List<Expense>): Double {
        return expenses.map { it.cost }.sum()
    }

    fun getBudgetById(id: Int): LiveData<Budget> {
        return budgetService.getBudgetById(id)
    }

    fun getBudgetWithExpensesById(id: Int): LiveData<BudgetWithExpenses> {
        return budgetService.getBudgetWithExpensesById(id)
    }

    fun addBudget(budget: Budget) {
        viewModelScope.launch {
            budgetService.createBudget(budget.month, budget.year, budget.amount)
        }
    }

    fun updateBudget(budget: Budget) {
        viewModelScope.launch {
            budgetService.updateBudget(budget)
        }
    }

    fun calculateBudget(expenses: List<Expense>, budgetValue: Double) : Double {
        val sumCost = expenseService.sumCostExpenses(expenses)
        return budgetValue - sumCost
    }
}