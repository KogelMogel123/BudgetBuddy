package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.model.Budget
import com.kogelmogel123.budgetbuddy.ui.components.SimpleDonutChart
import com.kogelmogel123.budgetbuddy.viewmodel.DashboardScreenViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.Month

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(viewModel: DashboardScreenViewModel = koinViewModel()) {
    val context = LocalContext.current
    val budget by viewModel.budget.observeAsState(initial = Budget(0, Month.MAY, 2024, 0.00))
    val expenses by viewModel.expenses.observeAsState(initial = emptyList())
    val pieChartData = viewModel.getDonutChartData(context, expenses)

    Column{
        if (pieChartData != null) {
            SimpleDonutChart(context, pieChartData)
            Text(text = "${stringResource(id = R.string.budget_for)} ${viewModel.currentDate.month}: ${budget?.amount ?: 0.00} zł",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 26.dp).fillMaxWidth())
        } else {
            Text(R.string.loading_data.toString(), Modifier.padding(16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}