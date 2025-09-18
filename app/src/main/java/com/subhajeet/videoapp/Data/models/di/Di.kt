package com.subhajeet.videoapp.Data.models.di

import com.subhajeet.videoapp.Data.models.repoimpil.RepoImpil
import com.subhajeet.videoapp.Domain.Repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object di{


    @Provides
    fun provideRepo(): Repo {
        return  RepoImpil()
    }
}