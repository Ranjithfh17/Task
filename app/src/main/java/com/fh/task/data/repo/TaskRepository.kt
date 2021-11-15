package com.fh.task.data.repo

import com.fh.task.data.local.TaskDao
import com.fh.task.data.model.AdventureInfo
import com.fh.task.data.remote.TaskApi
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskApi: TaskApi,
    private val taskDao: TaskDao
) {


    suspend fun getAdventureInfo() = taskApi.getAdventureInfo()

    suspend fun insertAdventure(adventureInfo: AdventureInfo) = taskDao.insertAdventure(adventureInfo)

    suspend fun deleteInfo(adventureInfo: AdventureInfo) =taskDao.deleteInfo(adventureInfo)
    suspend fun updateAdventure(adventureInfo: AdventureInfo) = taskDao.updateAdventure(adventureInfo)


    fun getAdventure() = taskDao.getAdventures()

    suspend fun deleteInfoTable() = taskDao.deleteInfoTable()



}