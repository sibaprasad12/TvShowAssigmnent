package com.assignment.distilled.di

import android.content.Context
import androidx.room.Room
import com.assignment.distilled.database.AppDatabase
import com.assignment.distilled.database.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sibaprasad Mohanty on 21/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "movie.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): TvShowDao {
        return appDatabase.tvShowDao()
    }
}