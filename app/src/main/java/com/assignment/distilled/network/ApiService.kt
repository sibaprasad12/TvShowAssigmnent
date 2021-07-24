package com.assignment.distilled.network

import com.assignment.distilled.data.MovieDbResponse
import com.assignment.distilled.data.TvShowData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface ApiService {

    /*@GET("top_rated")
    fun getPopTvShows(
        @Query("page") param1: Int?,
        @Query("language") language: String = "en-US"
    ): MovieDbResponse  */

    @GET("tv/top_rated?api_key=ef566fcdc1d67d697193c20688128c9d&amp;language=en-US&amp;page=1")
    suspend fun getPopTvShows(
    ): Response<MovieDbResponse>


    @GET("tv/top_rated")
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey: String? = "ef566fcdc1d67d697193c20688128c9d",
        @Query("language") language: String = "en-US",
        @Query("page") page: String
    ): Response<MovieDbResponse>

    @GET("tv/top_rated")
    suspend fun getMostPopularMovies1(
        @Query("api_key") apiKey: String? = "2cdf3a5c7cf412421485f89ace91e373",
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): MovieDbResponse

    @GET("top_rated")
    suspend fun getTvShows(): List<TvShowData>
}