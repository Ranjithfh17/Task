package com.fh.task.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class File(
    val mtime: String?=null,
    val name: String?=null,
    val source: String?=null,
):Parcelable