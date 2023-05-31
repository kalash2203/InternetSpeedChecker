package com.example.internetspeedchecker.di

import android.app.Application
import androidx.room.Room
import com.example.internetspeedchecker.data.local.db.SpeedDatabase
import com.example.internetspeedchecker.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesDatabase(app: Application): SpeedDatabase {
        return Room.databaseBuilder(
            app,
            SpeedDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }
}