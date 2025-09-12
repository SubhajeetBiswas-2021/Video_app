package com.subhajeet.videoapp.Data.models.repoimpil

import android.app.Application
import android.provider.MediaStore
import com.subhajeet.videoapp.Data.models.VideoModels
import com.subhajeet.videoapp.Domain.Repo.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//It is the implementation of the domain

class RepoImpil  : Repo {

    override suspend fun getAllVideos(application: Application): Flow<ArrayList<VideoModels>> {

        val videoList = ArrayList<VideoModels>()
        val projection = arrayOf(                                //Vedios ka kya kya chij uthana hai
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.TITLE,                     //If we want to take images then MediaStore.Images.Media.T
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_ADDED
        )

        val Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val memoryCursor = application.contentResolver.query(Uri,projection,null,null,MediaStore.Video.Media.DATE_ADDED + "DESC")

        if(memoryCursor != null && memoryCursor.count >0){
            while (memoryCursor.moveToNext()){

                val idC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))  //can also do memoryCursor.getString(0)
                val pathC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))   //can also do memoryCursor.getString(1)
                val titleC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE))
                val fileNameC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
                val sizeC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE))
                val durationC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))
                val dateAddedC = memoryCursor.getString(memoryCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED))

                videoList.add(VideoModels(idC,pathC,titleC,fileNameC,sizeC,durationC,dateAddedC))
            }

            memoryCursor.close()
        }
        return flow {
            emit(videoList)
        }
    }
}