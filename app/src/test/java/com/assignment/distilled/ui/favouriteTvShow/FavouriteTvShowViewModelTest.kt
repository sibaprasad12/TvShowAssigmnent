package com.assignment.distilled.ui.favouriteTvShow

import androidx.lifecycle.MutableLiveData
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.ui.testData.UnitTestData
import com.assignment.distilled.ui.tvShow.TvShowRepository
import com.assignment.distilled.ui.tvShow.TvShowViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FavouriteTvShowViewModelTest {

    private lateinit var fevViewModel: FavouriteTvShowViewModel
    private lateinit var viewModel: TvShowViewModel
    private lateinit var repository: TvShowRepository

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        repository = mockk<TvShowRepository>()
        fevViewModel = FavouriteTvShowViewModel(null, repository)
        viewModel = TvShowViewModel(null, repository)
    }

    @After
    fun setUpAfter() {

    }

    @Test
    fun insertAndCheckList() {
        /*viewModel.favouriteMeteorList.observeForever {
            Log.i("TAG", "ABCD")
        }
        val meteor = TestData.getTestData("test")
         meteoriewModel.insertFavoriteMeteors(meteor)
         viewModel.fetchMeteorsByPage()

         Mockito.`when`(viewModel.fetchMeteorsByPage())*/

        val mutableLiveData = MutableLiveData<TvShowData>()
        val meteorData = UnitTestData.getTestData("efgh", 101.2, 9.9, 1000, "en", "2021-07-25")
        mutableLiveData.postValue(meteorData)
        assertEquals(meteorData.name, mutableLiveData.value?.name)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun setObsevableLoading() {
    }

    @Test
    fun getLoadMore() {
    }

    @Test
    fun setLoadMore() {
    }

    @Test
    fun getErrorMessage() {
    }

    @Test
    fun setErrorMessage() {
    }

    @Test
    fun isValidTvShow() {
    }

    @Test
    fun isValidTvShowList() {
    }

    @Test
    fun getFavouriteTvShowList() {
    }

    @Test
    fun fetchTvSHowFromDatabaseByPage() {
    }

    @Test
    fun getAllFavouriteTvShows() {
    }
}