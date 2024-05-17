package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.models.PieChartData
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.service.IBudgetService
import com.kogelmogel123.budgetbuddy.service.IExpenseService
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month

class DashboardScreenViewModel(private val budgetService: IBudgetService, private val expenseService: IExpenseService) : ViewModel() {
    val currentDate = LocalDate.now()

    val expenses: LiveData<List<Expense>> = expenseService.getExpensesByLocalDate(currentDate).also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "Expenses data updated: ${data?.size} items")
        }
    }

    val budget:LiveData<Budget> = budgetService.getBudgetByDate(currentDate.month, currentDate.year).also {
        it.observeForever { data ->
            Log.d("DashboardScreenViewModel", "Budget data updated: ${data?.amount} zł")
        }
    }

    fun getDonutChartData(context: Context, expensesList: List<Expense>?): PieChartData? {
        val expenses = expensesList ?: return null

        val slices = ExpenseCategory.values().map { category ->
            val sum = sumExpensesByCategory(expenses, category)
            PieChartData.Slice(category.getLocalizedName(context), sum.toFloat(), getColorForCategory(category))
        }

        return PieChartData(slices = slices, plotType = PlotType.Donut)
    }

    fun createBudget(month: Month, year: Int, amount: Double)
    {
        viewModelScope.launch {
            budgetService.createBudget(month, year, amount)
        }
    }

    private fun getColorForCategory(category: ExpenseCategory): Color {
        return when (category) {
            ExpenseCategory.GROCERIES -> Color(0xFF5F0A87)
            ExpenseCategory.TRANSPORT -> Color(0xFF20BF55)
            ExpenseCategory.HOUSING -> Color(0xFFA40606)
            ExpenseCategory.ENTERTAINMENT -> Color(0xFFF53844)
            ExpenseCategory.CLOTHING -> Color(0xFFEC9F05)
            ExpenseCategory.EDUCATION -> Color(0xFF009FFD)
            ExpenseCategory.OTHER -> Color(0xFFBB1AD6)
        }
    }

    private fun sumExpensesByCategory(expenses: List<Expense>, category: ExpenseCategory): Double {
        return expenses.filter { it.category == category }.sumOf { it.cost }
        }
}