package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.controller.MovieApi
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.service.MovieApiService
import kotlinx.coroutines.withTimeout

class MovieRepository {

    /**
     * This is the class that contains the necessary methods to send a request to the API.
     * The method in this class uses the MovieApiService class to send a GET request.
     * The returned data is saved into the data model.
     */

    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _movieList: MutableLiveData<List<MovieItem>> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val movieList: LiveData<List<MovieItem>>
        get() = _movieList

    /**
     * Suspend function that calls a suspend function from the numbersApi call.
     */
    suspend fun getMovieList(year: Int) {
        try {
            // Timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getMovieList(year)
            }

            _movieList.value = result.results
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to refresh movies", error)
        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}