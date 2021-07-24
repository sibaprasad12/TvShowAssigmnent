package com.assignment.distilled.ui.base

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.assignment.distilled.data.TvShowData


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

open class BaseViewModel : ViewModel(), Observable {

    var obsevableLoading = ObservableBoolean(false)
    var loadMore = ObservableBoolean(false)
    var errorMessage = ObservableField("")

    fun isValidTvShow(tvSHow: TvShowData?): Boolean {
        return (tvSHow != null
                && !tvSHow.name.isNullOrEmpty())
    }

    fun isValidTvShowList(listTvShows: List<TvShowData>) =
        listTvShows.isNotEmpty()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}