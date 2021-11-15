package com.fh.task.callbacks

import com.fh.task.data.model.AdventureInfo
import com.fh.task.data.model.TaskInfo

interface OnAdventureCallback {

    fun onItemClick(taskInfo: AdventureInfo)


}