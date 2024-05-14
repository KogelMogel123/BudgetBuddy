package com.kogelmogel123.budgetbuddy.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.models.PieChartData
import com.kogelmogel123.budgetbuddy.data.IBudgetsRepository
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.model.MonthEnum
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BudgetViewModel(private val budgetsRepository: IBudgetsRepository) : ViewModel() {
    fun getDonutChartData(): PieChartData {
        return PieChartData(
            slices = listOf(
                PieChartData.Slice("GROCERIES", 1000f, Color(0xFF5F0A87)),
                PieChartData.Slice("TRANSPORT", 100f, Color(0xFF20BF55)),
                PieChartData.Slice("HOUSING", 200f, Color(0xFFA40606)),
                PieChartData.Slice("ENTERTAINMENT", 50f, Color(0xFFF53844)),
                PieChartData.Slice("CLOTHING", 350f, Color(0xFFEC9F05)),
                PieChartData.Slice("EDUCATION", 0f, Color(0xFF009FFD)),
                PieChartData.Slice("OTHER", 90f, Color(0xFFBB1AD6)),
            ),
            plotType = PlotType.Donut
        )
    }

    fun getBudgetById(id: Int): LiveData<Budget> {
        return budgetsRepository.getBudgetById(id)
            .catch { e ->
                Log.e("BudgetsViewModel", "Error fetching budget", e)
            }
            .asLiveData()
    }

    fun getBudgetByDate(month: MonthEnum, year: Int): LiveData<Budget> {
        return budgetsRepository.getBudgetByDate(month, year)
            .catch { e ->
                Log.e("BudgetsViewModel", "Error fetching budget", e)
            }
            .asLiveData()
    }

    fun addBudget(budget: Budget) {
        viewModelScope.launch {
            budgetsRepository.insertBudget(budget)
        }
    }
}