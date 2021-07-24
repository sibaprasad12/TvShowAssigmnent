package com.assignment.distilled.ui.favouriteTvShow

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.distilled.data.TvShowData
import com.assignment.distilled.network.ResponseState
import com.assignment.distilled.ui.base.BaseViewModel
import com.assignment.distilled.ui.tvShow.TvShowRepository
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
class FavouriteTvShowViewModel @Inject constructor(
    @ApplicationContext
    private val application: Context?,
    private val repository: TvShowRepository
) : BaseViewModel() {

    val favouriteTvShowList = MutableLiveData<ResponseState<List<TvShowData>>>()
    lateinit var viewModel: FavouriteTvShowViewModel

    private var limit = 10

    fun fetchTvSHowFromDatabaseByPage() {
        viewModelScope.launch {
            obsevableLoading.set(true)
            try {
                val numberOfRows = repository.getRowCount()
                limit = if (numberOfRows < limit) numberOfRows else limit
                val countriesFromDb = repository.getFavoriteMeteorsByPage(limit, 0)

                if (isValidTvShowList(countriesFromDb)) {
                    favouriteTvShowList.postValue(ResponseState.success(countriesFromDb))
                    errorMessage.set("")
                } else {
                    errorMessage.set("No Data found")
                }
                obsevableLoading.set(false)
            } catch (e: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(e.message ?: "Error Occurred!")
            }
        }
    }

    fun getAllFavouriteTvShows() {
        viewModelScope.launch {
            obsevableLoading.set(true)
            try {
                val countriesFromDb = repository.getAllFavoriteTvShow()
                if (isValidTvShowList(countriesFromDb)) {
                    favouriteTvShowList.postValue(ResponseState.success(countriesFromDb))
                } else {
                    favouriteTvShowList.postValue(ResponseState.error(emptyList(), "No Data found"))
                }
                obsevableLoading.set(false)
            } catch (e: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(e.message ?: "Error Occurred!")
            }
        }
    }

    fun removeFavoriteMeteors(meteor: TvShowData) {
        if (isValidTvShow(meteor)) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteTvShow(meteor)
                delay(1000)
                fetchTvSHowFromDatabaseByPage()
            }
        }
    }
}