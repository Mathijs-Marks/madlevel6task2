package com.example.madlevel6task2.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class MovieItem (

    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("rating") var rating: Int,
    @SerializedName("overview") var overview: String
        )