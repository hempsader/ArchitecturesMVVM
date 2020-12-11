package com.example.architecturesmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import java.util.concurrent.Flow

class AddViewModel(private val repository: LocalDataSource) : ViewModel(){

    val  title = ConflatedBroadcastChannel("")
    val releaseDate = ConflatedBroadcastChannel("")
    private val saveLiveData = MutableLiveData<Boolean>()



    fun getSavedLiveData(): LiveData<Boolean> = saveLiveData

     fun saveMovie() {
        if(title.value.isNotEmpty()){
            repository.insert(Movie(title = title.value, releaseDate = releaseDate.value))
            saveLiveData.postValue(true)
        }else{
            saveLiveData.postValue(false)
        }
    }
}