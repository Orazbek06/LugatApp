package uz.swlu.lugatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun MainNavigation(
    navController: NavHostController,
    homeScreen: @Composable () -> Unit,
    letterScreen: @Composable (String) -> Unit,
    aboutScreen: @Composable () -> Unit,
    aboutOwnerScreen: @Composable () -> Unit,
) {
    NavHost(navController = navController, Screen.HomeContent.route) {

        composable(Screen.HomeContent.route) {
            homeScreen()
        }

        composable(Screen.AboutContent.route) {
            aboutScreen()
        }

        composable(Screen.AboutOwnerContent.route) {
            aboutOwnerScreen()
        }

        composable(
            route = Screen.LetterContent.route,
            arguments = listOf(
                navArgument("letter") {
                    type = NavType.StringType
                }
            )
        ) {
            val list = it.arguments?.getString("letter")
            list?.let { id ->
                letterScreen(id)
            }
        }
    }
}

sealed class Screen(val route: String) {

    object AboutContent : Screen(ABOUT_ROUTE)

    object AboutOwnerContent : Screen(ABOUT_OWNER_ROUTE)

    object HomeContent : Screen(HOME_ROUTE)

    object LetterContent : Screen(LETTER_SCREEN_ROUTE) {
        fun getRouteWithArgs(id: String): String {
            return "letters/$id"
        }
    }

    private companion object {
        const val HOME_ROUTE = "home_screen"
        const val ABOUT_ROUTE = "about"
        const val ABOUT_OWNER_ROUTE = "about_owner"
        const val LETTER_SCREEN_ROUTE = "letters/{letter}"
    }
}
