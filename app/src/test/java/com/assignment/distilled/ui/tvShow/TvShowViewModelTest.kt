package com.assignment.distilled.ui.tvShow

import com.assignment.distilled.data.FilterData
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.ui.testData.UnitTestData
import com.assignment.distilled.utils.AppConstant
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var repository: TvShowRepository

    @Before
    @Throws(Exception::class)
    fun setUpBefore() {
        repository = mockk<TvShowRepository>()
        viewModel = TvShowViewModel(null, repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun filterTvshowsByHighPopularity() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.filterTvShow(FilterData("",0,AppConstant.FILTER_POPULARITY_HIGH))
        assertTrue(filteredData.size == 3)
    }

    @Test
    fun filterTvshowsByHighVote() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.filterTvShow(FilterData("",AppConstant.FILTER_VOTE_HIGH,0))
        assertTrue(filteredData.size == 2)
    }

    @Test
    fun filterTvshowsByLanguage() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.filterTvShow(FilterData("en",0,0))
        assertTrue(filteredData.size == 1)
    }

    @Test
    fun sortTvshowsByPopularity() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.sortTvShow(AppConstant.SORT_BY_POPULARITY)
        assertTrue(filteredData[0].popularity < filteredData[1].popularity)
    }

    @Test
    fun sortTvshowsByVoteAverage() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.sortTvShow(AppConstant.SORT_VOTE_AVERAGE)
        assertTrue(filteredData[0].vote_average < filteredData[1].vote_average)
    }

    @Test
    fun sortTvshowsByVoteCount() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.sortTvShow(AppConstant.SORT_VOTE_COUNT)
        assertTrue(filteredData[0].vote_count < filteredData[1].vote_count)
    }

    @Test
    fun sortTvshowsByAirDate() {
        viewModel.tvShowsListByPage = UnitTestData.getTvShowList()
        val filteredData = viewModel.sortTvShow(AppConstant.SORT_AIR_DATE)
        assertTrue(filteredData[0].first_air_date < filteredData[1].first_air_date)
    }

    // written in androidTest case
    @Test
    fun insertFavoriteTvSHow() {

    }

    @Test
    fun testValidTvShowData() {
        val nullValue: TvShowData? = null
        assertFalse(viewModel.isValidTvShow(nullValue))
        val validdata: TvShowData = UnitTestData.getTestData(
            "efgh", 101.2, 9.9, 1000, "en", "2021-07-25"
        )
        assertTrue(viewModel.isValidTvShow(validdata))
    }
}