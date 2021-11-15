package com.fh.task.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "adventure_info")
data class AdventureInfo(
    val name: String?,
    val source: String?,
    val mtime: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString()
):Parcelable

