package cats.will.rule.them.all.features.breeds.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import cats.composeapp.generated.resources.Res
import cats.composeapp.generated.resources.compose_multiplatform
import cats.will.rule.them.all.di.kodeinViewModel
import cats.will.rule.them.all.features.breeds.data.Breed
import org.jetbrains.compose.resources.painterResource

@Composable
fun LandingScreen(
    navigate: () -> Unit,
    viewModel: BreedsViewModel = kodeinViewModel(),
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
                    BreedList(
                        (state as LandingState.Success).pager.collectAsLazyPagingItems()
                    )
                }

                is LandingState.Error -> {
                    Text("Error")
                }
            }
        }
    }
}

@Composable
fun BreedList(pager: LazyPagingItems<Breed>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Defines 2 columns
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(pager.itemCount) { index ->
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = pager[index]?.name ?: "None")
            }
        }
        pager.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(top = 64.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text("Loading...")
                            Image(
                                painter = painterResource(Res.drawable.compose_multiplatform),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                            )
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    item {
                        Text("loadState refresh ${error.error.message}")
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.compose_multiplatform),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item {
                        Text("loadState refresh ${error.error.message}")
                    }
                }
            }
        }
    }
}