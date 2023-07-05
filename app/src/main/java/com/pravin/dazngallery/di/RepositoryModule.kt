package com.pravin.dazngallery.di

import android.content.Context
import com.pravin.dazngallery.domain.DashboardRepository
import com.pravin.dazngallery.data.DashboardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesDashboardRepository(@ApplicationContext context: Context): DashboardRepository {
        return DashboardRepositoryImpl(context)
    }
}