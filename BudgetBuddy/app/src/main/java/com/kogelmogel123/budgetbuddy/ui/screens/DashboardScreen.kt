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
import com.kogelmogel123.budgetbuddy.model.MonthEnum
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import com.kogelmogel123.budgetbuddy.ui.components.SimpleDonutChart

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(viewModel: BudgetViewModel = koinViewModel()) {
    val context = LocalContext.current
    val currentDate = LocalDate.now()
    val budgetLiveData = viewModel.getBudgetByDate(MonthEnum.MAY, 2024)
    val budget by budgetLiveData.observeAsState()

    Column{
        SimpleDonutChart(context, viewModel)

        Text(text = "${stringResource(id = R.string.budget_for)} ${budget?.month?.getLocalizedName(context) ?:currentDate.month}: ${budget?.amount ?: 0.00} zł",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 26.dp).fillMaxWidth())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}