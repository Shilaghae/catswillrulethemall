package cats.will.rule.them.all.features.landing

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object LandingScreen

fun NavGraphBuilder.goToLanding(
    navController: NavController,
) {
    composable<LandingScreen> {
        LandingScreen(
            navigate = {}
        )
    }
}