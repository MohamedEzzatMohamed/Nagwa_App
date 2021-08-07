package com.example.track_me.di

import android.app.Application
import com.example.track_me.dataBase.LocationDatabaseDao
import com.example.track_me.screens.location.LocationRepoInterface
import com.example.track_me.screens.location.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Location Repository module to inject the DatabaseDao into the repository
 */
@Module
@InstallIn(SingletonComponent::class)
object LocationRepositoryModule {

    @Singleton
    @Provides
    fun provideLocationRepository(
        application: Application,
        locationDao: LocationDatabaseDao
    ): LocationRepoInterface {
        return LocationRepository(
            application = application,
            locationDao = locationDao
        )
    }
}