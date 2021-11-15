package com.fh.task.data.remote

import com.fh.task.data.model.TaskInfo
import retrofit2.http.GET

interface TaskApi {

    @GET("TheAdventuresOfTomSawyer_201303")
    suspend fun getAdventureInfo(): TaskInfo

}