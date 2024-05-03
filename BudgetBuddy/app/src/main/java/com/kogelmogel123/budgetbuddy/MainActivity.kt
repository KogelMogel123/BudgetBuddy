package com.kogelmogel123.budgetbuddy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kogelmogel123.budgetbuddy.ui.theme.BudgetBuddyTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kogelmogel123.budgetbuddy.di.appModule
import com.kogelmogel123.budgetbuddy.ui.screens.AddExpenseScreen
import com.kogelmogel123.budgetbuddy.ui.screens.DashboardScreen
import com.kogelmogel123.budgetbuddy.ui.screens.EditExpenseScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ExpensesScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ReceiptsImagesScreen
import com.kogelmogel123.budgetbuddy.ui.screens.ScanReceiptScreen
import com.kogelmogel123.budgetbuddy.ui.screens.camera.CameraPreviewScreen
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
                                    title = stringResource(id = R.string.dashboard_screen),
                                    contentDescription = "Go to dashboard screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "expenses",
                                    title = stringResource(id = R.string.expenses_screen),
                                    contentDescription = "Go to expenses screen",
                                    icon = Icons.Default.AccountBalance
                                ),
                                MenuItem(
                                    id = "scanReceipt",
                                    title = stringResource(id = R.string.scan_receipt_screen),
                                    contentDescription = "Go to scan receipt screen",
                                    icon = Icons.Default.Search
                                ),
                                MenuItem(
                                    id = "receiptsImagesScreen",
                                    title = stringResource(id = R.string.receipt_analysis_screen),
                                    contentDescription = "Go to receipt analysis screen",
                                    icon = Icons.Default.AutoAwesome
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
                            ScanReceiptScreen(navController = navController)
                        }
                        composable("expenses") {
                            ExpensesScreen(navController = navController)
                        }
                        composable("addExpense") {
                            AddExpenseScreen(navController = navController)
                        }
                        composable("receiptsImagesScreen") {
                            ReceiptsImagesScreen()
                        }
                        composable(
                            route = "receiptsImagesScreen/{selectedImageEncodedUri}",
                            arguments = listOf(navArgument("selectedImageEncodedUri") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val selectedImageEncodedUri = backStackEntry.arguments?.getString("selectedImageEncodedUri")
                            ReceiptsImagesScreen(selectedImageEncodedUri = selectedImageEncodedUri)
                        }
                        composable(
                            route = "editExpense/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val expenseId = backStackEntry.arguments?.getInt("id") ?: -1
                            EditExpenseScreen(navController = navController, id = expenseId)
                        }
                        composable("cameraPreviewScreen") {
                            CameraPreviewScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}