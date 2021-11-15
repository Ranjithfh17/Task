package com.fh.task.data.local

import androidx.room.TypeConverter
import com.fh.task.data.model.File
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<File>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<File>::class.java).toList()




}