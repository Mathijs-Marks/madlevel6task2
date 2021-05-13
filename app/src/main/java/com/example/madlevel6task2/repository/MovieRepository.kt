package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.controller.MovieApi
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.service.MovieApiService
import kotlinx.coroutines.withTimeout

class MovieRepository {

    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movie: MutableLiveData<MovieItem> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val movie: LiveData<MovieItem>
        get() = _movie

    /**
     * Suspend function that calls a suspend function from the numbersApi call.
     */
    suspend fun getMovieList() {
        try {
            // Timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getMovieList()
            }

            _movie.value = result
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh movies", error)
        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}