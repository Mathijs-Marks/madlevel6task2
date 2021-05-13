package com.example.madlevel6task2.service

import com.example.madlevel6task2.model.MovieItem
import retrofit2.http.GET

interface MovieApiService {

    // The GET method needed to retrieve the list of movies
    @GET("/3/discover/movie?api_key=<<api_key>>&language=en-US&sort_by=popularity.desc&include_adult=true&include_video=false&page=1&with_watch_monetization_types=flatrate")
    suspend fun getMovieList(): MovieItem
}