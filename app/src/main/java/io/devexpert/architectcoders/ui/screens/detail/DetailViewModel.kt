package io.devexpert.architectcoders.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.devexpert.architectcoders.Result
import io.devexpert.architectcoders.data.Movie
import io.devexpert.architectcoders.domain.FindMovieByIdUseCase
import io.devexpert.architectcoders.domain.ToggleFavoriteUseCase
import io.devexpert.architectcoders.ifSuccess
import io.devexpert.architectcoders.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    id: Int,
    findMovieByIdUseCase: FindMovieByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val state: StateFlow<Result<Movie>> = findMovieByIdUseCase(id)
        .stateAsResultIn(scope = viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess {
            viewModelScope.launch {
                toggleFavoriteUseCase(it)
            }
        }
    }
}