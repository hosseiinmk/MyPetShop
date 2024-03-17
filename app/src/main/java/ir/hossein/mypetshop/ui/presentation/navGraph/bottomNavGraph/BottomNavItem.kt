package ir.hossein.mypetshop.ui.presentation.navGraph.bottomNavGraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ir.hossein.mypetshop.ui.presentation.navGraph.NavigationDestination

sealed class BottomNavItems(val route: String, val icon: ImageVector, val label: String) {

    data object Home : BottomNavItems(
        route = NavigationDestination.Home.route,
        icon = Icons.Default.Home,
        label = "خانه"
    )

    data object AddProduct : BottomNavItems(
        route = NavigationDestination.AddProduct.route,
        icon = Icons.Default.Add,
        label = "افزودن محصول"
    )

    data object Profile : BottomNavItems(
        route = NavigationDestination.Profile.route,
        icon = Icons.Default.Person,
        label = "پروفایل"
    )
}