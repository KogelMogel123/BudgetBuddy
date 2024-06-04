package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.models.PieChartData
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.model.User
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import com.kogelmogel123.budgetbuddy.service.IExpenseService
import com.kogelmogel123.budgetbuddy.service.IUserService
import java.time.LocalDate

class DashboardScreenViewModel(private val budgetService: IBudgetService, private val expenseService: IExpenseService, private val userService: IUserService) : ViewModel() {
    val currentDate = LocalDate.now()

    val expenses: LiveData<List<Expense>> = expenseService.getExpensesByLocalDate(currentDate).also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "Expenses: ${data?.size} items")
        }
    }

    val budget:LiveData<Budget> = budgetService.getBudgetByDate(currentDate.month, currentDate.year).also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "Budget: ${data?.amount} zł")
        }
    }

    val user: LiveData<User> = userService.getMe().also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "User: ${data?.name}")
        }
    }

    fun getDonutChartData(context: Context, expensesList: List<Expense>?): PieChartData? {
        val expenses = expensesList ?: return null

        val slices = ExpenseCategory.values().map { category ->
            val sum = expenseService.sumExpensesByCategory(expenses, category)
            PieChartData.Slice(category.getLocalizedName(context), sum.toFloat(), getColorForCategory(category))
        }

        return PieChartData(slices = slices, plotType = PlotType.Donut)
    }

    private fun getColorForCategory(category: ExpenseCategory): Color {
        return when (category) {
            ExpenseCategory.GROCERIES -> Color(0xFF28A745)
            ExpenseCategory.TRANSPORT -> Color(0xFFB22222)
            ExpenseCategory.HOUSING -> Color(0xFF6A0DAD)
            ExpenseCategory.ENTERTAINMENT -> Color(0xFFFF8C00)
            ExpenseCategory.CLOTHING -> Color(0xFF1E90FF)
            ExpenseCategory.EDUCATION -> Color(0xFF7E3D0B)
            ExpenseCategory.OTHER -> Color(0xFFFFF933)
        }
    }

    fun calculateBudget(expenses: List<Expense>, budgetValue: Double) : Double {
        val sumCost = expenseService.sumCostExpenses(expenses)
        return budgetValue - sumCost
    }
}