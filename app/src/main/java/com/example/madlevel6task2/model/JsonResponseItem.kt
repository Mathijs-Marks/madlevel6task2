package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class JsonResponseItem (

    /**
     * When requesting a list of movies from the API, I receive a Json document that lists all
     * movies. Since this is a single object, I can't really work with it.
     * To solve this, I take the values from the Json document and put it into a data model.
     */

    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<MovieItem>
        )