package com.example.madlevel6task2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(

    /**
     * This data model contains all necessary components that I want to request from the API.
     * I make this model Parcelable to be able to pass it between fragments.
     */

    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("vote_average") var rating: Double,
    @SerializedName("overview") var overview: String
        ) : Parcelable
{
    // Since I'm not receiving any image files, but rather an image URL, I use these methods to retrieve them.
    fun getBackdropPathUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
    fun getPosterPathUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
}