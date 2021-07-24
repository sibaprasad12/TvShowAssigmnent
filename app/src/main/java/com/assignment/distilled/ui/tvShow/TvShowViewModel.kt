package com.assignment.distilled.ui.tvShow

import android.content.Context
import androidx.lifecycle.liveData
import com.assignment.distilled.data.FilterData
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.network.ResponseState
import com.assignment.distilled.ui.base.BaseViewModel
import com.assignment.distilled.utils.AppConstant
import com.assignment.distilled.utils.AppConstant.Companion.DEFAULT
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@HiltViewModel
class TvShowViewModel @Inject constructor(
    @ApplicationContext private val application: Context?,
    private val tvShowRepository: TvShowRepository
) :
    BaseViewModel() {

    private var currentPage = 0
    private var filterType = DEFAULT
    private var tvShowsListByPage = ArrayList<TvShowData>()
    private var tvShowListFilter: List<TvShowData> = listOf()

    fun getTvShows(isRefresh: Boolean = false) = liveData(Dispatchers.IO) {
        obsevableLoading.set(true)
        if (isRefresh) currentPage = 1
        try {
            tvShowRepository.getTopTvShows(++currentPage).let {
                if (it.isSuccessful) {
                    tvShowsListByPage = it.body()?.results as ArrayList<TvShowData>
                    emit(ResponseState.success(tvShowsListByPage))
                    insertAllMeteors(tvShowsListByPage)
                    delay(2000)
                    obsevableLoading.set(false)
                    errorMessage.set("")
                } else {
                    obsevableLoading.set(false)
                    errorMessage.set("Error Occurred!")
                }
            }
        } catch (exception: Exception) {
            obsevableLoading.set(false)
            errorMessage.set(exception.message ?: "Error Occurred!")
            emit(ResponseState.error(null, exception.message ?: "Error Occurred!"))
        }
    }


    fun getTvSHowsFromDatabase() = liveData(Dispatchers.IO) {
        if (tvShowsListByPage.isEmpty()) {
            obsevableLoading.set(true)
            try {
                val listTvShowsFromDb = tvShowRepository.getAllSavedTvShow()
                tvShowsListByPage = listTvShowsFromDb as ArrayList<TvShowData>
                emit(ResponseState.success(tvShowsListByPage))
                obsevableLoading.set(false)
                errorMessage.set("")
            } catch (exception: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(exception.message ?: "Error Occurred!")
                emit(ResponseState.error(null, exception.message ?: "Error Occurred!"))
            }
        } else {
            obsevableLoading.set(false)
            emit(ResponseState.success(tvShowsListByPage))
        }
    }

    fun filterTvShow(filterData: FilterData): List<TvShowData> {
        val filteredItems = tvShowsListByPage.filter {
            (filterData.language.isEmpty() || filterData.language == it.original_language) && filterData.voteAverage <= it.vote_average  && filterData.popularity <= it.popularity
        }
        if(filteredItems.isEmpty()) errorMessage.set("No Data Found")
        return filteredItems
    }
    fun sortTvShow(filter: Int) = when (filter) {
        AppConstant.SORT_BY_POPULARITY -> {
            filterType = filter
            tvShowsListByPage.sortedBy {
                it.popularity
            }
        }
        AppConstant.SORT_BY_NAME -> {
            filterType = filter
            tvShowsListByPage.sortedBy {
                it.name
            }
        }
        AppConstant.SORT_AIR_DATE -> {
            filterType = filter
            tvShowsListByPage.sortedBy {
                it.first_air_date
            }
        }
        AppConstant.SORT_VOTE_AVERAGE -> {
            filterType = filter
            tvShowsListByPage.sortedBy {
                it.vote_average
            }
        }
        AppConstant.SORT_VOTE_COUNT -> {
            filterType = filter
            tvShowsListByPage.sortedBy {
                it.vote_count
            }
        }
        else -> {
            filterType = DEFAULT
            tvShowsListByPage
        }
    }

    fun insertFavoriteMeteors(tvshow: TvShowData) {
        if (isValidTvShow(tvshow)) {
            tvshow.isFavourite = 1
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    tvShowRepository.insertTvShow(tvshow)
                    delay(1000)
                } catch (e: java.lang.Exception) {
                    tvShowRepository.updateTvShow(tvshow)
                }
            }
        }
    }

    private fun insertAllMeteors(tvshows: List<TvShowData>) {
        if (isValidTvShowList(tvshows)) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    tvShowRepository.insertAllTvShow(tvshows)
                    delay(1000)
                } catch (e: java.lang.Exception) {

                }
            }
        }
    }
    fun isValidMeteorList() = super.isValidTvShowList(tvShowsListByPage)
}
