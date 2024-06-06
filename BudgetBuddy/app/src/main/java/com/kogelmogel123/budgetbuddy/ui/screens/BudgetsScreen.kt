package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.R
import com.kogelmogel123.budgetbuddy.ui.components.BudgetItemComponent
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BudgetsScreen(viewModel: BudgetViewModel = koinViewModel(), navController: NavController) {
    val budgetsWithExpenses by viewModel.budgetsWithExpenses.observeAsState(initial = emptyList())

    Scaffold(
    )

    { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text(text = "${stringResource(id = R.string.list_of_declared_budgets)}:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
            LazyColumn {
                items(budgetsWithExpenses) { budgetWithExpenses ->
                    val leftInTheBudget = budgetWithExpenses.budget?.let { viewModel.calculateBudget(budgetWithExpenses.expenses, it.amount) } ?: 0.0
                    val spentPercentage = budgetWithExpenses.budget?.let {
                        val spent = it.amount - (leftInTheBudget ?: it.amount)
                        if (it.amount > 0) spent / it.amount else 0.0
                    } ?: 0.0
                    BudgetItemComponent(budgetWithExpenses, leftInTheBudget, spentPercentage,
                        onEdit = { navController.navigate("editBudgetScreen/${budgetWithExpenses.budget.id}") },
                        onShowExpenses = { navController.navigate("budgetWithExpensesScreen/${budgetWithExpenses.budget.id}") })
                }
            }
        }
    }
}