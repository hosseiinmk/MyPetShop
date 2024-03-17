package ir.hossein.mypetshop.ui.presentation.navGraph

sealed class NavigationDestination(val route: String) {
    data object Splash: NavigationDestination(route = "splash")
    data object Main: NavigationDestination(route = "main")
    data object Home: NavigationDestination(route = "home")
    data object AddProduct: NavigationDestination(route = "add_product")
    data object Profile: NavigationDestination(route = "profile")
}