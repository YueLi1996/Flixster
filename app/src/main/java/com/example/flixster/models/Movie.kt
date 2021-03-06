package com.example.flixster.models

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONObject
import org.json.JSONArray
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val MovieId: Int,
    val voteAverage:Double,
    private val posterPath: String,
    private val backdropPath: String,
    val title:String,
    val overview:String
) : Parcelable {
    @IgnoredOnParcel
    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"
    @IgnoredOnParcel
    val backdropImageURL = "https://image.tmdb.org/t/p/w342/$backdropPath"

    companion object {
        fun fromJsonArray(movieJSONArray: JSONArray) : List<Movie> {
            val movies = mutableListOf<Movie>() //empty array
            for (i in 0 until movieJSONArray.length()) {
                val movieJson : JSONObject = movieJSONArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("backdrop_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                    )
                )
            }
            return movies
        }
    }
}