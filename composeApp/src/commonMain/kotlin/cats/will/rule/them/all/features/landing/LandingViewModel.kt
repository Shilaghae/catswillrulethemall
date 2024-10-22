package cats.will.rule.them.all.features.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LandingViewModel(
    private val breedRepository: BreedRepository
) : ViewModel() { // ViewModel is a part of the Android Jetpack library
    private val _state = MutableStateFlow<LandingState>(LandingState.Loading)
    val state: StateFlow<LandingState> get() = _state

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch {
            _state.emit(LandingState.Loading)
            val breed = breedRepository.getBreeds()
            _state.emit(LandingState.Success(breed.map { it.name + "\n" }.toString()))
        }
    }
}

sealed interface LandingState {
    data object Loading : LandingState
    data class Success(val breed: String) : LandingState
    data object Error : LandingState
}
