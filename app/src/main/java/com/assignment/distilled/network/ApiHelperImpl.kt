package com.assignment.distilled.network

/**
 * Created by Sibaprasad Mohanty on 17/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

import com.assignment.distilled.data.MovieDbResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getTvShows() = apiService.getPopTvShows()
    override suspend fun getTopTvShows(page: Int): Response<MovieDbResponse> =
        apiService.getMostPopularMovies(page = "$page")
}