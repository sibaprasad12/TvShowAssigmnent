package com.assignment.distilled.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/**
 * Created by Sibaprasad Mohanty on 21/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Keep
data class MovieDbResponse(
    val page: Int, // 1
    val results: List<TvShowData>,
    val total_pages: Int, // 97
    val total_results: Int // 1931
)

@Keep
@Entity
@Parcelize
data class TvShowData(
    val backdrop_path: String,
    val first_air_date: String,
    @PrimaryKey val id: Int,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
    var isFavourite: Int = 0,
    var position: Int = -1
) : BaseObservable(), Parcelable {

    constructor(
        backdrop_path: String,
        first_air_date: String,
        genre_ids: List<Int>,
        id: Int,
        name: String,
        origin_country: List<String>,
        original_language: String,
        original_name: String,
        overview: String,
        popularity: Double,
        poster_path: String,
        vote_average: Double,
        vote_count: Int,
        isFavourite: Int = 0,
        position: Int = -1
    ) : this(
        backdrop_path,
        first_air_date,
        id,
        name,
        original_language,
        original_name,
        overview,
        popularity,
        poster_path,
        vote_average,
        vote_count,
        isFavourite,
        position
    )
}