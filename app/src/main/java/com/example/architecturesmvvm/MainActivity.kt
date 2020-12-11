package com.example.architecturesmvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie
import com.example.architecturesmvvm.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private val viewModelMain by lazy {
        ViewModelProvider(this,object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(LocalDataSource(application)) as T
            }
        })[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movies_recyclerview.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModelMain.getMovieList().observe(this, Observer {movies->
            if(movies.isNotEmpty()){
                no_movies_layout.visibility = View.GONE
                movies_recyclerview.apply {
                   this.adapter = ListMovieRecycler(movies)
                }
            }else{
                no_movies_layout.visibility = View.VISIBLE
            }
        })

        addMovieActivity()
    }

    fun addMovieActivity(){
        fab.setOnClickListener {
            startActivity(Intent(this, AddMovieActivity::class.java))
        }
    }

}