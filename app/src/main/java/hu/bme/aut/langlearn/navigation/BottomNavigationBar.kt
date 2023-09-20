package hu.bme.aut.langlearn.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<NavItem>,
    onItemClick: (NavItem) -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar {
        items.forEach { item ->
            val isSelected = item.route == navBackStackEntry?.destination?.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                icon = {
                    if (isSelected) {
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = item.label
                        )
                    } else {
                        Icon(
                            imageVector = item.unSelectedIcon,
                            contentDescription = item.label
                        )
                    }
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}