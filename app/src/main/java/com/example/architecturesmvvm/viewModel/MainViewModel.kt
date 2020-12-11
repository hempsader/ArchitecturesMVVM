package com.example.architecturesmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie

class MainViewModel(private val repository: LocalDataSource): ViewModel(){
    private val movieListLiveData = MutableLiveData<List<Movie>>()

    init {
        movieListLiveData.postValue(repository.getAllMvovies())
    }
    fun getMovieList(): LiveData<List<Movie>> = movieListLiveData
}