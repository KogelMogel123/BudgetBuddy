package com.kogelmogel123.budgetbuddy.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kogelmogel123.budgetbuddy.viewmodel.BudgetViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BudgetsScreen(viewModel: BudgetViewModel = koinViewModel(), navController: NavController) {
    Text(text = "Summary of budgets")
}