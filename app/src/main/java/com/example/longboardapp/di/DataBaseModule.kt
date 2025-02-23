package com.example.longboardapp.di

import android.content.Context
import androidx.room.Room
import com.example.longboardapp.data.LongBoardsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule{

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext appContext: Context): LongBoardsDataBase {
        return Room.databaseBuilder(
            appContext,
            LongBoardsDataBase::class.java, "long_boards_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLongBoardsDao(db: LongBoardsDataBase) = db.getLongBoardDao()
}