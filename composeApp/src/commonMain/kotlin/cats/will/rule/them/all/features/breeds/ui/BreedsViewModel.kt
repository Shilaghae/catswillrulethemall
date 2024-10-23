package cats.will.rule.them.all.features.breeds.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cats.will.rule.them.all.features.breeds.data.Breed
import cats.will.rule.them.all.features.breeds.data.BreedPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BreedsViewModel(
    private val breedPagingSource: BreedPagingSource
) : ViewModel() {
    private val _state = MutableStateFlow<LandingState>(LandingState.Loading)
    val state: StateFlow<LandingState> get() = _state

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch {
            _state.emit(LandingState.Loading)
            runCatching {
                val pager = Pager(
                    config = PagingConfig(pageSize = 10),
                    pagingSourceFactory = { breedPagingSource }
                ).flow
                _state.value = LandingState.Success(pager)
            }.onFailure {
                _state.value = LandingState.Error
            }
        }
    }
}

//UI Model
sealed interface LandingState {
    data object Loading : LandingState
    data class Success(val pager: Flow<PagingData<Breed>>) : LandingState
    data object Error : LandingState
}


