package io.devexpert.architectcoders.domain.movie.usecases

import io.devexpert.architectcoders.domain.movie.data.MoviesRepository
import io.devexpert.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies
}