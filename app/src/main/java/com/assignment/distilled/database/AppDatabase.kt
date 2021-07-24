package com.assignment.distilled.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.distilled.data.TvShowData

/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Database(entities = [TvShowData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tvShowDao(): TvShowDao
}