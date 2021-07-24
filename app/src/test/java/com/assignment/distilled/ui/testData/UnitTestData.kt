package com.assignment.distilled.ui.testData

import com.assignment.distilled.data.TvShowData


/**
 * Created by Sibaprasad Mohanty on 23/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class UnitTestData {
    companion object {
        fun getTvShowList(): ArrayList<TvShowData> {
            val list = ArrayList<TvShowData>()
            list.add(getTestData("efgh", "101", "2001-01-03", 0))
            list.add(getTestData("abcd", "100", "2000-01-03", 1))
            list.add(getTestData("mnop", "103", "2003-01-03", 1))
            list.add(getTestData("ijkl", "102", "2002-01-03", 1))
            return list
        }

        fun getFavouriteTvShowList(): ArrayList<TvShowData> {
            val list = ArrayList<TvShowData>()
            list.add(getTestData("efgh", "101", "2001-01-03", 1))
            list.add(getTestData("abcd", "100", "2000-01-03", 1))
            list.add(getTestData("mnop", "103", "2003-01-03", 1))
            list.add(getTestData("ijkl", "102", "2002-01-03", 1))
            return list
        }

        fun getTestData(
            name: String,
            overView: String = "Overview Overview Overview Overview",
            airDate: String = "2021-07-25",
            isfavourite: Int = 1
        ): TvShowData {
            return TvShowData(
                "Fell",
                airDate,
                1,
                name,
                "En",
                name,
                overView,
                1.2,
                "posterPath",
                1.2,
                2,
                0,
                0
            )
        }
    }
}