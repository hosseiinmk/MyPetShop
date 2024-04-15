package ir.hossein.mypetshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hossein.mypetshop.ui.presentation.splash.SplashScreen

@Composable
fun RootNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.Splash.route
    ) {
        composable(route = NavigationDestination.Splash.route) {
            SplashScreen {
                navController.navigate(route = NavigationDestination.Main.route) {
                    popUpTo(route = NavigationDestination.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = NavigationDestination.Main.route) {
            MainNavGraph()
        }
    }
}