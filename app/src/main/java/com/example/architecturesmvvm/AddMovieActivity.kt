package com.example.architecturesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architectures.model.LocalDataSource
import com.example.architecturesmvvm.viewModel.AddViewModel
import com.example.architecturesmvvm.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddMovieActivity : AppCompatActivity() {
    private val viewModelAdd by lazy {
        ViewModelProvider(this,object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return AddViewModel(LocalDataSource(application)) as T
            }
        })[AddViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        viewModelAdd.getSavedLiveData().observe(this,  Observer {
            if(it){
                viewModelAdd.saveMovie()
                finish()
            }
        })

        addTitleAndYear()
    }

    fun addTitleAndYear(){
        addMovieButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                viewModelAdd.title.send(movie_title.text.toString())
                viewModelAdd.releaseDate.send(movie_release_date.text.toString())
            }
        }
    }
}