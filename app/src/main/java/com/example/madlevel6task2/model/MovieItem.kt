package com.example.madlevel6task2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(

    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("rating") var rating: Int.Companion,
    @SerializedName("overview") var overview: String
        ) : Parcelable
{
    fun getBackdropPathUrl() = "https://image.tmdb.org/t/p/w500$backdropPath"
    fun getPosterPathUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
}