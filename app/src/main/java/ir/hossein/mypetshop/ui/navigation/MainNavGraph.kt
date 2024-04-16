package ir.hossein.mypetshop.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hossein.mypetshop.core.bottomNavItemSelected
import ir.hossein.mypetshop.ui.presentation.profile.ProfileScreen
import ir.hossein.mypetshop.ui.presentation.addProduct.AddProduct
import ir.hossein.mypetshop.ui.presentation.home.HomeScreen
import ir.hossein.mypetshop.ui.component.bottomNavigationBar.BottomNavigationBar

@Composable
fun MainNavGraph(navController: NavHostController = rememberNavController()) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar { route ->
                navController.navigate(route = route) {
                    popUpTo(NavigationDestination.Home.route) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { transitionIntoContainer() },
            exitTransition = { transitionOutOfContainer() }
        ) {
            composable(
                route = NavigationDestination.Home.route
            ) {
                HomeScreen()
            }
            composable(route = NavigationDestination.AddProduct.route) {
                AddProduct(
                    goToHome = {
                        navController.navigateUp()
                        bottomNavItemSelected.value = 2
                    }
                )
            }
            composable(
                route = NavigationDestination.Profile.route
            ) {
                ProfileScreen(
                    goToHome = {
                        navController.navigateUp()
                        bottomNavItemSelected.value = 2
                    }
                )
            }
        }
    }
}