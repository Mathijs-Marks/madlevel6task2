package com.example.madlevel6task2.service

import com.example.madlevel6task2.model.JsonResponseItem
import com.example.madlevel6task2.model.MovieItem
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    /**
     * This class is the component that actually talks to the API by sending a GET request,
     * containing a Url and a condition that the most popular movies of that specific year are received.
     */

    // The GET method needed to retrieve the list of movies
    @GET("/3/discover/movie?api_key=1d43f3188d91cfa5e2558fcf3e63362a&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    suspend fun getMovieList(@Query("year") year: Int): JsonResponseItem
}