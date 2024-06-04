package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.helper.DateHelper
import com.kogelmogel123.budgetbuddy.ui.components.SimpleDonutChart
import com.kogelmogel123.budgetbuddy.ui.screens.preview.mockNavController
import com.kogelmogel123.budgetbuddy.viewmodel.DashboardScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(viewModel: DashboardScreenViewModel = koinViewModel(), navController: NavController) {
    val context = LocalContext.current
    val budget by viewModel.budget.observeAsState(initial = null)
    val expenses by viewModel.expenses.observeAsState(initial = emptyList())
    val pieChartData = viewModel.getDonutChartData(context, expenses)
    val leftInTheBudget = budget?.let { viewModel.calculateBudget(expenses, it.amount) }
    val spentPercentage = budget?.let {
        val spent = it.amount - (leftInTheBudget ?: it.amount)
        if (it.amount > 0) spent / it.amount else 0.0
    } ?: 0.0

    Column(modifier = Modifier.padding(4.dp)){
        if (pieChartData != null) {
            SimpleDonutChart(context, pieChartData)
        } else {
            Text(R.string.loading_data.toString(), Modifier.padding(16.dp))
        }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 26.dp, start = 14.dp)
                    .fillMaxWidth()
            ) {
                if(budget == null) {
                    Text(text = stringResource(id = R.string.no_set_budget_for_this_month),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f))
                    Button(
                        onClick = { navController.navigate("addBudgetScreen") },
                        modifier = Modifier.padding(start = 8.dp))
                    {
                        Text(text = stringResource(id = R.string.set_a_budget))
                    }
                }
                else{
                    Text(
                        text = "${stringResource(id = R.string.budget_for)} ${ DateHelper.getLocalizedName(context, viewModel.currentDate.month)}: ${String.format("%.2f", budget?.amount ?: 0.00) ?: "0.00"} zł",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { navController.navigate("editBudgetScreen/${budget?.id}") },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = stringResource(id = R.string.modify_the_budget))
                    }
                }
            }

            Text(
                text = "${stringResource(id = R.string.left_in_the_budget)}: ${String.format("%.2f", leftInTheBudget ?: 0.00) ?: "0.00"} zł",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(top = 26.dp, start = 14.dp)
                    .fillMaxWidth()
            )
            LinearProgressIndicator(
                progress = { spentPercentage.toFloat() },
                color = Color(0xFF4CAF50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .padding(horizontal = 14.dp, vertical = 8.dp),
            )
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = mockNavController())
}