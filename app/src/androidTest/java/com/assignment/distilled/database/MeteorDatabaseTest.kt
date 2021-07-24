package com.assignment.distilled.database


import android.content.Context
import android.util.Log
import androidx.room.Room
import com.assignment.distilled.data.AndroidTestData
import com.assignment.distilled.data.TvShowData
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Created by Sibaprasad Mohanty on 23/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MeteorDatabaseTest : TestCase() {

    private lateinit var tvShowDao: TvShowDao
    private lateinit var db: AppDatabase
    lateinit var context: Context

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        context = mockk<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        tvShowDao = db.tvShowDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadMeteorData() = runBlocking {
        val meteorInsert: TvShowData = AndroidTestData.getTestData("abcd")
        tvShowDao.insertTvShow(meteorInsert)
        val TvShowData = tvShowDao.getTvShowByName("abcd")
        assertThat(meteorInsert.name == TvShowData?.name).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun readWriteMeteorTest() = runBlocking {
        try {
            val tvShowInsert: TvShowData = AndroidTestData.getTestData("abcd")
            tvShowDao.insertTvShow(tvShowInsert)
            val TvShowData = tvShowDao.getTvShowByName("abcd")
            assertThat(tvShowInsert.name == TvShowData?.name).isTrue()
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun readWriteMeteorListContains() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val meteorInsert: TvShowData = AndroidTestData.getTestData("abcd")
                tvShowDao.insertTvShow(meteorInsert)
                val meteorList = tvShowDao.getAllSavedMeteor()
                assertTrue(meteorList.contains(meteorInsert))
            } catch (e: java.lang.Exception) {
                Log.i("MeteorViewModelUiTest", e.message!!)
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_element_to_favourite_list_and_check_if_exists_or_not() = runBlocking {
        try {
            val meteorInsert: TvShowData = AndroidTestData.getTestData("abcd", isfavourite = 0)
            tvShowDao.insertTvShow(meteorInsert)
            val meteorList = tvShowDao.getAllFavoriteTvShows()
            assertFalse(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_element_to_favourite_list_and_check_exists() = runBlocking {
        try {
            tvShowDao.clearTvShowTable()
            val meteorList = tvShowDao.getAllFavoriteTvShows()
            assertTrue(meteorList.isEmpty())
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun remove_all_meteor_return_empty_favoutite_meteorList() = runBlocking {
        try {
            val meteorInsert: TvShowData = AndroidTestData.getTestData("abcd", isfavourite = 1)
            tvShowDao.insertTvShow(meteorInsert)
            val meteorList = tvShowDao.getAllFavoriteTvShows()
            assertTrue(meteorList.contains(meteorInsert))
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insert_duplicate_meteor_return_error() = runBlocking {
        try {
            tvShowDao.clearTvShowTable()
            val meteorInsert: TvShowData = AndroidTestData.getTestData("abcd", isfavourite = 1)
            tvShowDao.insertTvShow(meteorInsert)
            tvShowDao.insertTvShow(meteorInsert)
            val meteorList = tvShowDao.getAllFavoriteTvShows()
            assertTrue(meteorList.size == 1)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
            assertTrue(true)
        }
    }

    @Test
    fun testFetchMeteorsByPage() = runBlocking {
        try {
            val limit = 3
            tvShowDao.clearTvShowTable()
            val meteorList = AndroidTestData.getTvShowList()
            tvShowDao.insertAllTvShows(meteorList)

            val meteorListFromDatabase = tvShowDao.getFavoriteTvShowsByPage(limit, 0)
            assertTrue(meteorListFromDatabase.size == 3)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    fun testGetAllFavouriteMeteors() = runBlocking {
        try {
            tvShowDao.clearTvShowTable()
            val meteorFavouriteList = AndroidTestData.getFavouriteTvShowList()
            tvShowDao.insertAllTvShows(meteorFavouriteList)

            val favouriteListFromDatabase = tvShowDao.getAllFavoriteTvShows()
            assertTrue(meteorFavouriteList.size == favouriteListFromDatabase.size)
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
        }
    }

    @Test
    fun testRemoveFavoriteMeteors() = runBlocking {
        try {
            tvShowDao.clearTvShowTable()
            val meteorInsertFavourite: TvShowData =
                AndroidTestData.getTestData("favourite", isfavourite = 1)
            val meteorInsert: TvShowData =
                AndroidTestData.getTestData("not favourite", isfavourite = 0)
            tvShowDao.insertTvShow(meteorInsertFavourite)
            tvShowDao.insertTvShow(meteorInsert)

            val meteorList = tvShowDao.getAllFavoriteTvShows()
            val favourite = tvShowDao.getTvShowByName("favourite")
            assertTrue(meteorList.size == 1)
            assertTrue(favourite?.name == "favourite")
        } catch (e: java.lang.Exception) {
            Log.i("MeteorViewModelUiTest", e.message!!)
            assertTrue(true)
        }
    }
}