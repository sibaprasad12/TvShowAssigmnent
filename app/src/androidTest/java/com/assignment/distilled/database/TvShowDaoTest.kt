package com.assignment.meteoriteapp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.assignment.distilled.data.AndroidTestData
import com.assignment.distilled.database.AppDatabase
import com.assignment.distilled.database.TvShowDao
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class TvShowDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var tvShowDao: TvShowDao

    @Before
    fun setup() {
        hiltRule.inject()
        tvShowDao = database.tvShowDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val tvShow = AndroidTestData.getTestData("abcd")
        tvShowDao.insertTvShow(tvShow)
        val allUsers = tvShowDao.getAllSavedMeteor()
        assertThat(allUsers).contains(tvShow)

    }
}