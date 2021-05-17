package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class JsonResponseItem (

    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<MovieItem>
        )