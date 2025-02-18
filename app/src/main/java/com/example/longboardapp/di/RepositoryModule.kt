package com.example.longboardapp.di

import com.example.longboardapp.data.repository.LocalLongBoardsRepository
import com.example.longboardapp.domain.LongBoardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindLocalStorageRepository(
        localLongBoardsRepository: LocalLongBoardsRepository
    ): LongBoardsRepository
}