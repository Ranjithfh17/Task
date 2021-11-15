package com.fh.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fh.task.Utils.Resource
import com.fh.task.data.model.AdventureInfo
import com.fh.task.data.model.TaskInfo
import com.fh.task.data.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {


    private val _infoLiveData = MutableLiveData<Resource<TaskInfo>>()
    val infoLiveData: LiveData<Resource<TaskInfo>> = _infoLiveData


    init {
        getAdventureInfo()
    }


    private fun getAdventureInfo() = viewModelScope.launch {
        try {
            _infoLiveData.value = Resource.Loading()
            val response = repository.getAdventureInfo()
            _infoLiveData.value = Resource.Success(response)


        } catch (exception: Exception) {
            _infoLiveData.value = Resource.Error(exception.message.toString())

        }
    }


    fun insertAdventure(adventureInfo: AdventureInfo) = viewModelScope.launch {
        repository.insertAdventure(adventureInfo)
    }

    fun deleteInfo(adventureInfo: AdventureInfo) = viewModelScope.launch {
        repository.deleteInfo(adventureInfo)

    }

    fun updateAdventure(adventureInfo: AdventureInfo) = viewModelScope.launch {
        repository.updateAdventure(adventureInfo)

    }


    fun getAdventures() = repository.getAdventure()

    fun deleteInfoTable() = viewModelScope.launch {
        repository.deleteInfoTable()
    }


}