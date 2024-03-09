package com.kogelmogel123.budgetbuddy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kogelmogel123.budgetbuddy.ui.theme.BudgetBuddyTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import com.kogelmogel123.budgetbuddy.screens.AddExpensesScreen
import com.kogelmogel123.budgetbuddy.screens.DashboardScreen
import com.kogelmogel123.budgetbuddy.screens.InformationScreen
import com.kogelmogel123.budgetbuddy.screens.ScanReceiptScreen
import com.kogelmogel123.budgetbuddy.screens.SettingsScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainVm by viewModels<MainViewModel>()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            DashboardScreen(onClick = {
                                navController.navigate(
                                    route = it,
                                    navOptions { popUpTo("dashboard") { inclusive = true } })
                            })
                        }
                        composable("scanReceipt") {
                            ScanReceiptScreen(onClick = {
                                navController.navigate(
                                    route = it,
                                    navOptions { popUpTo("dashboard") { inclusive = true } })
                            })
                        }
                        composable("addExpenses") {
                            AddExpensesScreen(onClick = {
                                navController.navigate(
                                    route = it,
                                    navOptions { popUpTo("dashboard") { inclusive = true } })
                            })
                        }
                        composable("settings") {
                            SettingsScreen(onClick = {
                                navController.navigate(
                                    route = it,
                                    navOptions { popUpTo("dashboard") { inclusive = true } })
                            })
                        }
                        composable("information") {
                            InformationScreen(onClick = {
                                navController.navigate(
                                    route = it,
                                    navOptions { popUpTo("dashboard") { inclusive = true } })
                            })
                        }
                    }
                }
            }
        }
    }
}