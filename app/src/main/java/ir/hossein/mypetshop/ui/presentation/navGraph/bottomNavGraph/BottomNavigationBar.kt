package ir.hossein.mypetshop.ui.presentation.navGraph.bottomNavGraph

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.hossein.mypetshop.core.bottomNavItemSelected

@Composable
fun BottomNavigationBar(
    goToDestination: (String) -> Unit
) {

    val items = listOf(
        BottomNavItems.Profile,
        BottomNavItems.AddProduct,
        BottomNavItems.Home
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = bottomNavItemSelected.value == index,
                onClick = {
                    bottomNavItemSelected.value = index
                    goToDestination(item.route)
                },
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(text = item.label) }
            )
        }
    }
}