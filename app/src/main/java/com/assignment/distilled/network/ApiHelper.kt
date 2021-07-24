package com.assignment.distilled.network

import com.assignment.distilled.data.MovieDbResponse
import retrofit2.Response

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface ApiHelper {
    suspend fun getTvShows(): Response<MovieDbResponse>
    suspend fun getTopTvShows(page: Int): Response<MovieDbResponse>
}