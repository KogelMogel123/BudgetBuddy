package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.ui.components.BudgetItemComponent
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BudgetsScreen(viewModel: BudgetViewModel = koinViewModel(), navController: NavController) {
    val budgets by viewModel.budgets.observeAsState(initial = emptyList())

    LazyColumn {
        items(budgets) { budget ->
            BudgetItemComponent(budget)
        }
    }
}