package cats.will.rule.them.all

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import cats.will.rule.them.all.features.landing.LandingScreen
import cats.will.rule.them.all.features.landing.goToLanding
import org.jetbrains.compose.ui.tooling.preview.Preview
import theming.CatsAppTheme

@Composable
@Preview
fun App() {
    CatsAppTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val navController = rememberNavController()
            NavHost(
                navController,
                startDestination = LandingScreen
            ) {
                goToLanding(
                    navController = navController,
                )
            }
        }
    }
}