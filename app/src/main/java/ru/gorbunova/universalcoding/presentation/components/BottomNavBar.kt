package ru.gorbunova.universalcoding.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Ballot
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.outlined.Ballot
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.gorbunova.universalcoding.presentation.navigation.NavRoute
import ru.gorbunova.universalcoding.presentation.navigation.screens
/*import ru.gorbunova.universalcoding.utils.Constants.TEST
import ru.gorbunova.universalcoding.utils.Constants.THEORY*/

@Composable
fun BottomNavBar(navController: NavController) {
    val icons = mapOf(
        NavRoute.Theory to ItemGroup(
            filledIcon = Icons.Filled.Book,
            outlineIcon = Icons.Outlined.Book,
            label = ""
            //label = THEORY
        ),
        NavRoute.Test_Start to ItemGroup(
            filledIcon = Icons.Filled.Ballot,
            outlineIcon = Icons.Outlined.Ballot,
            label = ""
            //label = TEST
        )
    )

    NavigationBar(
        Modifier
        .fillMaxWidth()
        .height(60.dp)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val labelText = icons[screen]!!.label
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlineIcon),
                        contentDescription = labelText
                    )
                },
                label = { Text(labelText) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}