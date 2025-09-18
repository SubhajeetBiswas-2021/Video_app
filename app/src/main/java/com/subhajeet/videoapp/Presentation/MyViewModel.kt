package com.subhajeet.videoapp.Presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.videoapp.Data.models.VideoModels
import com.subhajeet.videoapp.Domain.Repo.useCase.GetAllVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val getAllVideosUseCase: GetAllVideosUseCase , val application: Application) : ViewModel() {

    val videosList = MutableStateFlow(emptyList<VideoModels>())

    init {
        loadAllVideos()
    }
    //val videosList = MutableStateFlow<List<VideoModels>>(emptyList())



    fun loadAllVideos(){
        viewModelScope.launch {
            getAllVideosUseCase.getAllVideosUserCase(application)
                .collectLatest {
                    videosList.value = it
                }
        }
    }

}