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
            list.add(getTestData("efgh", 101.2, 9.9, 1000, "en", "2021-07-25", 0))
            list.add(getTestData("abcd", 102.2, 10.0, 900, "ja", "2021-07-25", 1))
            list.add(getTestData("mnop", 90.1, 5.5, 1100, "ko", "2021-07-25", 1))
            list.add(getTestData("ijkl", 110.2, 6.5, 1300, "df", "2021-07-25", 0))
            return list
        }

        fun getFavouriteTvShowList(): ArrayList<TvShowData> {
            val list = ArrayList<TvShowData>()
            list.add(getTestData("efgh", 101.2, 9.9, 1000, "en", "2021-07-25"))
            list.add(getTestData("abcd", 102.2, 10.0, 900, "ja", "2021-07-25"))
            list.add(getTestData("mnop", 90.1, 5.5, 1100, "ko", "2021-07-25"))
            list.add(getTestData("ijkl", 110.2, 6.5, 1300, "df", "2021-07-25"))
            return list
        }

        fun getTestData(
            name: String,
            popularity: Double,
            voteAverage: Double,
            voteCount: Int,
            language: String,
            airDate: String = "2021-07-25",
            isfavourite: Int = 1
        ): TvShowData {
            return TvShowData(
                backdrop_path = "",
                first_air_date = airDate,
                id = 1,
                name = name,
                original_language = language,
                original_name = name,
                overview = "overview",
                popularity = popularity,
                poster_path = "",
                vote_average = voteAverage,
                vote_count = voteCount,
                isFavourite = isfavourite,
                position = 1
            )
        }
    }
}