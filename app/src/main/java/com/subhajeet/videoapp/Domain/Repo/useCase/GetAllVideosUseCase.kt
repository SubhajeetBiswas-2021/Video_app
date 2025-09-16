package com.subhajeet.videoapp.Domain.Repo.useCase

import android.app.Application
import com.subhajeet.videoapp.Domain.Repo.Repo
import javax.inject.Inject

class GetAllVideosUseCase @Inject constructor(private val repo:Repo) {

    suspend fun getAllVideosUserCase(application: Application) = repo.getAllVideos(application)
}