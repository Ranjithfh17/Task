package com.fh.task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fh.task.data.model.AdventureInfo

@Database(entities = [AdventureInfo::class], version = 1)

@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}