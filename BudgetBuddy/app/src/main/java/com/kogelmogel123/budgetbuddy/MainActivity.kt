package com.kogelmogel123.budgetbuddy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kogelmogel123.budgetbuddy.ui.theme.BudgetBuddyTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kogelmogel123.budgetbuddy.di.appModule
import com.kogelmogel123.budgetbuddy.ui.screens.AddExpensesScreen
import com.kogelmogel123.budgetbuddy.ui.screens.DashboardScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ExpensesScreen
import com.kogelmogel123.budgetbuddy.ui.screens.InformationScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ScanReceiptScreen
import com.kogelmogel123.budgetbuddy.ui.screens.SettingsScreen
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Koin
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            BudgetBuddyTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "dashboard",
                                    title = stringResource(id = R.string.dashboard),
                                    contentDescription = "Go to dashboard screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "scanReceipt",
                                    title = stringResource(id = R.string.scanReceipt),
                                    contentDescription = "Go to scan receipt screen",
                                    icon = Icons.Default.Search
                                ),
                                MenuItem(
                                    id = "addExpenses",
                                    title = stringResource(id = R.string.addExpenses),
                                    contentDescription = "Go to add expenses screen",
                                    icon = Icons.Default.Add
                                ),
                                MenuItem(
                                    id = "expenses",
                                    title = stringResource(id = R.string.expenses),
                                    contentDescription = "Go to expenses screen",
                                    icon = Icons.Default.AccountBalance
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = stringResource(id = R.string.settings),
                                    contentDescription = "Go to settings screen",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "information",
                                    title = stringResource(id = R.string.information),
                                    contentDescription = "Get information",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {menuItem ->
                                println("Clicked on ${menuItem.title}")
                                navController.navigate(menuItem.id)
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    }
                ) {
                    NavHost(navController, startDestination = "dashboard") {
                        composable("dashboard") {
                            DashboardScreen()
                        }
                        composable("scanReceipt") {
                            ScanReceiptScreen(onClick = {
                                navController.navigate(route = it)
                            })
                        }
                        composable("addExpenses") {
                            AddExpensesScreen(onClick = {
                                navController.navigate(route = it)
                            })
                        }
                        composable("expenses") {
                            ExpensesScreen()
                        }
                        composable("settings") {
                            SettingsScreen(onClick = {
                                navController.navigate(route = it)
                            })
                        }
                        composable("information") {
                            InformationScreen(onClick = {
                                navController.navigate(route = it)
                            })
                        }
                    }
                }
            }
        }
    }
}