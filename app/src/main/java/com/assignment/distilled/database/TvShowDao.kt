package com.assignment.distilled.database

import androidx.room.*
import com.assignment.distilled.data.TvShowData


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTvShow(tvshowData: TvShowData)

    @Delete
    suspend fun deleteTvShow(tvshowData: TvShowData)

    @Update
    suspend fun updateTvShow(tvshowData: TvShowData)

    @Query("SELECT * from tvshowdata")
    suspend fun getAllSavedMeteor(): List<TvShowData>


    @Query("SELECT * FROM tvshowdata WHERE isFavourite > 0")
    suspend fun getAllFavoriteTvShows(): List<TvShowData>

    @Query("SELECT * FROM tvshowdata LIMIT :limit OFFSET :offset")
    suspend fun loadSavedTvShowsByPage(limit: Int, offset: Int): List<TvShowData>

    @Query("SELECT COUNT(name) FROM tvshowdata")
    suspend fun getRowCount(): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAllTvShows(tvshowList: List<TvShowData>)

    @Query("SELECT * FROM tvshowdata WHERE isFavourite > 0 LIMIT :limit OFFSET :offset")
    suspend fun getFavoriteTvShowsByPage(limit: Int, offset: Int): List<TvShowData>

    @Query("SELECT * FROM tvshowdata WHERE name =:name")
    suspend fun getTvShowByName(name: String): TvShowData?

    @Query("DELETE FROM tvshowdata")
    suspend fun clearTvShowTable()

}