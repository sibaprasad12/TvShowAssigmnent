package com.assignment.distilled.ui.tvShow

import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.database.TvShowDao
import com.assignment.distilled.network.ApiHelperImpl
import javax.inject.Inject


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class TvShowRepository @Inject constructor(
    private val apiHelper: ApiHelperImpl,
    private val tvShowDao: TvShowDao
) {
    suspend fun getTvShows() = apiHelper.getTvShows()
    suspend fun getTopTvShows(page: Int) = apiHelper.getTopTvShows(page)
    suspend fun getAllSavedTvShow() = tvShowDao.getAllSavedMeteor()
    suspend fun insertTvShow(tvShow: TvShowData) = tvShowDao.insertTvShow(tvShow)
    suspend fun insertAllTvShow(tvShows: List<TvShowData>) = tvShowDao.insertAllTvShows(tvShows)
    suspend fun updateTvShow(tvShowData: TvShowData) =
        tvShowDao.updateTvShow(tvShowData)

    suspend fun getRowCount() = tvShowDao.getRowCount()
    suspend fun getFavoriteMeteorsByPage(limit: Int, offset: Int) =
        tvShowDao.getFavoriteTvShowsByPage(limit, offset)

    suspend fun getTvShowByName(tvSHowName: String) = tvShowDao.getTvShowByName(tvSHowName)
    suspend fun clearTvShowTable() = tvShowDao.clearTvShowTable()
    suspend fun deleteTvShow(tvShow: TvShowData) = tvShowDao.deleteTvShow(tvShow)
    suspend fun getAllFavoriteTvShow() = tvShowDao.getAllFavoriteTvShows()
}