package com.example.longboardapp.di

import com.example.longboardapp.data.repository.LocalLongBoardsRepository
import com.example.longboardapp.data.repository.LocalUserRepository
import com.example.longboardapp.domain.LongBoardsRepository
import com.example.longboardapp.domain.UserRepository
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

    @Singleton
    @Binds
    abstract fun bindUserLocalStorageRepository(
        localUserRepository: LocalUserRepository
    ): UserRepository
}