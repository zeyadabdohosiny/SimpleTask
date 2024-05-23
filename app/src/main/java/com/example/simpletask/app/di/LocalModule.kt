package com.example.simpletask.app.di


import android.content.Context

import androidx.datastore.preferences.createDataStore
import androidx.room.Room

import com.example.simpletask.app.constants.AppConstants
import com.example.simpletask.data.local.room.AppDataBase
import com.example.simpletask.data.local.room.dao_interfaces.ChatDao
import com.example.simpletask.data.repository.ChatRepositoryImpl
import com.example.simpletask.domain.repository.ChatRepositoryInterface

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "room_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) =
        context.createDataStore(AppConstants.PREF_NAME)

    @Provides
    @Singleton
    fun provideChatDao(appDatabase: AppDataBase): ChatDao {
        return appDatabase.chatDao()
    }

    @Provides
    @Singleton
    fun provideChatRepository(chatDao: ChatDao): ChatRepositoryInterface {
        return ChatRepositoryImpl(chatDao)
    }


}