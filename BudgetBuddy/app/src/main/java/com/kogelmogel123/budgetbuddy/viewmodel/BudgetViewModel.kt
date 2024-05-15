package com.kogelmogel123.budgetbuddy.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.models.PieChartData
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.data.IExpensesRepository
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.Expense
import com.kogelmogel123.budgetbuddy.model.ExpenseCategory
import com.kogelmogel123.budgetbuddy.model.MonthEnum
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.time.LocalDate

class BudgetViewModel(private val budgetsRepository: IBudgetsRepository, private val expensesRepository: IExpensesRepository) : ViewModel() {
    fun getDonutChartData(context: Context): PieChartData {
        val currentDate = LocalDate.now()
        val dateRange = DateHelper.getMonthDateRange(currentDate.year, currentDate.month)
        val expensesLiveData1 = expensesRepository.getAll()
        val expensesLiveData = expensesRepository.getByStartEndDate(dateRange.first, dateRange.second)

        val xx = sumExpensesByCategory(expensesLiveData1.value ?: emptyList(), ExpenseCategory.GROCERIES)
        val xx2 = sumExpensesByCategory(expensesLiveData.value ?: emptyList(), ExpenseCategory.TRANSPORT)
        val xx3 = sumExpensesByCategory(expensesLiveData.value ?: emptyList(), ExpenseCategory.HOUSING)
        val xx4 = sumExpensesByCategory(expensesLiveData.value ?: emptyList(), ExpenseCategory.ENTERTAINMENT)
        val xx5 = sumExpensesByCategory(expensesLiveData.value ?: emptyList(), ExpenseCategory.CLOTHING)
        val xx6 = sumExpensesByCategory(expensesLiveData.value ?: emptyList(), ExpenseCategory.EDUCATION)
        val xx7 = sumExpensesByCategory(expensesLiveData.value ?: emptyList(), ExpenseCategory.OTHER)


        return PieChartData(
            slices = listOf(
                PieChartData.Slice(ExpenseCategory.GROCERIES.getLocalizedName(context), xx.toFloat(), Color(0xFF5F0A87)),
                PieChartData.Slice(ExpenseCategory.TRANSPORT.getLocalizedName(context), xx2.toFloat(), Color(0xFF20BF55)),
                PieChartData.Slice(ExpenseCategory.HOUSING.getLocalizedName(context), xx3.toFloat(), Color(0xFFA40606)),
                PieChartData.Slice(ExpenseCategory.ENTERTAINMENT.getLocalizedName(context), xx4.toFloat(), Color(0xFFF53844)),
                PieChartData.Slice(ExpenseCategory.CLOTHING.getLocalizedName(context), xx5.toFloat(), Color(0xFFEC9F05)),
                PieChartData.Slice(ExpenseCategory.EDUCATION.getLocalizedName(context), xx6.toFloat(), Color(0xFF009FFD)),
                PieChartData.Slice(ExpenseCategory.OTHER.getLocalizedName(context), xx7.toFloat(), Color(0xFFBB1AD6)),
            ),
            plotType = PlotType.Donut
        )
    }

    fun sumExpensesByCategory(expenses: List<Expense>, category: ExpenseCategory): Double {
        return expenses
            .filter { it.category == category }
            .sumOf { it.cost }
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

    fun getBudgetById(id: Int): LiveData<Budget> {
        return budgetsRepository.getById(id)
            .catch { e ->
                Log.e("BudgetsViewModel", "Error fetching budget", e)
            }
            .asLiveData()
    }

    fun getBudgetByDate(month: MonthEnum, year: Int): LiveData<Budget> {
        return budgetsRepository.getByDate(month, year)
            .catch { e ->
                Log.e("BudgetsViewModel", "Error fetching budget", e)
            }
            .asLiveData()
    }

    fun addBudget(budget: Budget) {
        viewModelScope.launch {
            budgetsRepository.insert(budget)
        }
    }
}