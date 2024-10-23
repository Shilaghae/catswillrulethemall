package cats.will.rule.them.all.features.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cats.will.rule.them.all.di.kodeinViewModel

@Composable
fun LandingScreen(
    navigate: () -> Unit,
    viewModel: LandingViewModel = kodeinViewModel(),
) {
    Scaffold {
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is LandingState.Loading -> {
                    Text("Loading...")
                }
                is LandingState.Success -> {
                    Text((state as LandingState.Success).breed)
                }
                is LandingState.Error -> {
                    Text("Error")
                }
            }
        }
    }
}