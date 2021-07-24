package com.assignment.distilled.controller

import com.assignment.distilled.data.TvShowData


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

interface MovieClickListener {
    fun onTvShowClick(tvShowData: TvShowData)
    fun onFevouriteClick(tvShowData: TvShowData)
}