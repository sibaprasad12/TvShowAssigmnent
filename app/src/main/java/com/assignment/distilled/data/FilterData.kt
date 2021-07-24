package com.assignment.distilled.data


/**
 * Created by Sibaprasad Mohanty on 23/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

data class FilterData(
    var language: String,
    var voteAverage: Int = 10,
    var popularity: Int = 50
)