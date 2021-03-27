package com.example.rynningtrackapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.rynningtrackapp.db.RunningDatabase
import com.example.rynningtrackapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.rynningtrackapp.other.Constants.KEY_NAME
import com.example.rynningtrackapp.other.Constants.KEY_WEIGHT
import com.example.rynningtrackapp.other.Constants.RUNNING_DATABASE_NAME
import com.example.rynningtrackapp.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//this module will be created when Application will be created//
//we want our instance of db exist till our app exist thatis why we use ApplicationComponent
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton // to have only ONE instance everywhere
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app:Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun providesWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f) ?: ""

    @Singleton
    @Provides
    fun providesFirstTimeToggle(sharedPref: SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true)
}
