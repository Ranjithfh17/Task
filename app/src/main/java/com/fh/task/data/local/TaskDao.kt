package com.fh.task.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fh.task.data.model.AdventureInfo
import com.fh.task.data.model.File
import com.fh.task.data.model.TaskInfo

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdventure(adventureInfo: AdventureInfo)

    @Query("SELECT * FROM adventure_info")
    fun getAdventures(): LiveData<List<AdventureInfo>>

    @Delete
    suspend fun deleteInfo(adventureInfo: AdventureInfo)

    @Update
    suspend fun updateAdventure(adventureInfo: AdventureInfo)

    @Query("DELETE FROM adventure_info")
    suspend fun deleteInfoTable()



}