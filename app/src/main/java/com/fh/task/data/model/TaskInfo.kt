package com.fh.task.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "task_info")
data class TaskInfo(
    val files: List<File>,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
):Parcelable